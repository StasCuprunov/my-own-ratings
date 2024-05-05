package en.ratings.own.my.service.rating;

import en.ratings.own.my.exception.rating.RatingByIdNotFoundException;
import en.ratings.own.my.exception.rating.creation.RatingCreationFailedException;
import en.ratings.own.my.exception.rating.update.RatingUpdateFailedException;
import en.ratings.own.my.model.User;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import en.ratings.own.my.repository.UserRepository;
import en.ratings.own.my.repository.rating.RangeOfValuesRepository;
import en.ratings.own.my.repository.rating.RatingEntryRepository;
import en.ratings.own.my.repository.rating.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static en.ratings.own.my.constant.AttributeConstants.STEP_WIDTH_BORDER;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRIES_DONT_FIT_IN_SCALE;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_USER_WITH_ID_NOT_FOUND;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MINIMUM_IS_NOT_VALID;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_STEP_WIDTH_IS_TOO_SMALL;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RANGE_OF_VALUES_IS_NOT_CONSISTENT;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_NAME_ALREADY_USED_FOR_USER;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_BY_ID_NOT_FOUND;
import static en.ratings.own.my.service.rating.RangeOfValuesValidation.isRangeOfValuesConsistent;
import static en.ratings.own.my.service.rating.RangeOfValuesValidation.isValueInRangeOfValues;
import static en.ratings.own.my.utility.StringUtility.addExistentStringToArrayList;

@Service
public class RatingService {

    private final UserRepository userRepository;

    private final RatingRepository ratingRepository;

    private final RangeOfValuesRepository rangeOfValuesRepository;

    private final RatingEntryRepository ratingEntryRepository;

    @Autowired
    public RatingService(UserRepository userRepository, RatingRepository ratingRepository,
                         RangeOfValuesRepository rangeOfValuesRepository, RatingEntryRepository ratingEntryRepository) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.rangeOfValuesRepository = rangeOfValuesRepository;
        this.ratingEntryRepository = ratingEntryRepository;
    }

    public Rating findById(Long id) throws Exception {
        Optional<Rating> rating = findRatingById(id);

        if (rating.isEmpty()) {
            throw new RatingByIdNotFoundException(id);
        }
        return rating.get();
    }

    public ArrayList<Rating> findAllByUserId(Long userId) {
        return findAllRatingsByUserId(userId).get();
    }

    public Rating create(Long userId, RangeOfValues rangeOfValues, String name, String description) throws Exception {
        ArrayList<String> keysForException = rangeOfValuesValidation(rangeOfValues);
        keysForException = addExistentStringToArrayList(keysForException, userIdValidation(userId));
        keysForException = addExistentStringToArrayList(keysForException, ratingNameForUserValidation(userId, name));

        if (!keysForException.isEmpty()) {
            throw new RatingCreationFailedException(keysForException);
        }
        return createRating(userId, rangeOfValues, name, description);
    }

    public Rating update(Long id, Long userId, RangeOfValues rangeOfValues, String name, String description)
            throws Exception {
        ArrayList<String> keysForException = rangeOfValuesValidation(rangeOfValues);
        keysForException = addExistentStringToArrayList(keysForException, ratingIdValidation(id));
        if (!keysForException.isEmpty()) {
            throw new RatingUpdateFailedException(keysForException);
        }

        keysForException = addExistentStringToArrayList(keysForException, ratingNameForUpdateValidation(userId, name));

        ArrayList<String> ratingEntries = ratingEntriesDontFitInNewRangeOfValues(id, rangeOfValues);
        if (!ratingEntries.isEmpty()) {
            keysForException = addExistentStringToArrayList(keysForException, KEY_RATING_ENTRIES_DONT_FIT_IN_SCALE);
        }
        if (!keysForException.isEmpty()) {
            throw new RatingUpdateFailedException(keysForException, ratingEntries);
        }
        return updateRating(id, userId, rangeOfValues, name, description);
    }

    public void deleteById(Long id) {
        deleteRatingById(id);
    }

    private Rating createRating(Long userId, RangeOfValues rangeOfValues, String name, String description) {
        return updateRating(null, userId, rangeOfValues, name, description);
    }

    private Rating updateRating(Long ratingId, Long userId, RangeOfValues rangeOfValues, String name,
                                String description) {
        Long rangeOfValuesId;
        Long oldRangeOfValuesId = null;

        Optional<RangeOfValues> oldRangeOfValues = findRangeOfValuesByMinimumAndMaximumAndStepWidth(rangeOfValues);

        if (oldRangeOfValues.isPresent()) {
            oldRangeOfValuesId = oldRangeOfValues.get().getId();
            rangeOfValuesId = oldRangeOfValuesId;
        }
        else {
            rangeOfValuesId = saveRangeOfValues(rangeOfValues).getId();
        }

        Rating rating = new Rating(userId, rangeOfValuesId, name, description);
        if (ratingId != null) {
            rating.setId(ratingId);
        }

        rating = saveRating(rating);

        if (oldRangeOfValuesId != null) {
            deleteRangeOfValuesIfNotUsed(oldRangeOfValuesId);
        }
        return rating;
    }

    private String userIdValidation(Long id) {
        if (findUserById(id).isEmpty()) {
            return KEY_USER_WITH_ID_NOT_FOUND;
        }
        return null;
    }

    private ArrayList<String> rangeOfValuesValidation(RangeOfValues rangeOfValues) {
        ArrayList<String> keysForException = new ArrayList<>();
        if (rangeOfValues.getMinimum() >= rangeOfValues.getMaximum()) {
            keysForException.add(KEY_MINIMUM_IS_NOT_VALID);
        }
        if (rangeOfValues.getStepWidth() < STEP_WIDTH_BORDER) {
            keysForException.add(KEY_STEP_WIDTH_IS_TOO_SMALL);
        }

        if (!isRangeOfValuesConsistent(rangeOfValues)) {
            keysForException.add(KEY_RANGE_OF_VALUES_IS_NOT_CONSISTENT);
        }
        return keysForException;
    }

    private String ratingNameForUserValidation(Long userId, String name) {
        if (findRatingByUserIdAndName(userId, name).isPresent()) {
            return KEY_RATING_NAME_ALREADY_USED_FOR_USER;
        }
        return null;
    }

    private String ratingIdValidation(Long id) {
        Optional<Rating> rating = findRatingById(id);

        if (rating.isEmpty()) {
            return KEY_RATING_BY_ID_NOT_FOUND;
        }
        return null;
    }

    private String ratingNameForUpdateValidation(Long userId, String name) {
        return ratingNameForUserValidation(userId, name);
    }

    private ArrayList<String> ratingEntriesDontFitInNewRangeOfValues(Long ratingId, RangeOfValues rangeOfValues) {
        ArrayList<RatingEntry> ratingEntries = findAllRatingEntriesByRatingId(ratingId).get();

        ArrayList<String> ratingEntriesDontFitInScale = new ArrayList<>();

        for (RatingEntry ratingEntry: ratingEntries) {
            if (!isValueInRangeOfValues(ratingEntry.getValue(), rangeOfValues)) {
                ratingEntriesDontFitInScale.add(ratingEntry.getName());
            }
        }
        return ratingEntriesDontFitInScale;
    }

    private Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    private Optional<Rating> findRatingById(Long id) {
        return ratingRepository.findById(id);
    }

    private Optional<Rating> findRatingByUserIdAndName(Long userId, String name) {
        return ratingRepository.findByUserIdAndName(userId, name);
    }

    private Optional<ArrayList<Rating>> findAllRatingsByUserId(Long userId) {
        return ratingRepository.findAllByUserId(userId);
    }

    private boolean existsRatingByRangeOfValuesId(Long rangeOfValuesId) {
        return ratingRepository.existsByRangeOfValuesId(rangeOfValuesId);
    }

    private Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    private void deleteRatingById(long id) {
        ratingRepository.deleteById(id);
    }

    private RangeOfValues saveRangeOfValues(RangeOfValues rangeOfValues) {
        return rangeOfValuesRepository.save(rangeOfValues);
    }

    private Optional<RangeOfValues> findRangeOfValuesByMinimumAndMaximumAndStepWidth(RangeOfValues rangeOfValues) {
        return rangeOfValuesRepository.findByMinimumAndMaximumAndStepWidth(rangeOfValues.getMinimum(),
                rangeOfValues.getMaximum(), rangeOfValues.getStepWidth());
    }

    private void deleteRangeOfValuesById(Long id) {
        rangeOfValuesRepository.deleteById(id);
    }

    private void deleteRangeOfValuesIfNotUsed(Long id) {
        if (!existsRatingByRangeOfValuesId(id)) {
            deleteRangeOfValuesById(id);
        }
    }

    private Optional<ArrayList<RatingEntry>> findAllRatingEntriesByRatingId(Long ratingId) {
        return ratingEntryRepository.findAllByRatingId(ratingId);
    }
}
