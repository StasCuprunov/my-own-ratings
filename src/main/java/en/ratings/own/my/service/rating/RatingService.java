package en.ratings.own.my.service.rating;

import en.ratings.own.my.exception.rating.RatingByIdNotFoundException;
import en.ratings.own.my.exception.rating.RatingsByUserIdNotFoundException;
import en.ratings.own.my.exception.rating.creation.RatingCreationFailedException;
import en.ratings.own.my.exception.rating.update.RatingUpdateFailedException;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import en.ratings.own.my.service.repository.UserRepositoryService;
import en.ratings.own.my.service.repository.rating.RangeOfValuesRepositoryService;
import en.ratings.own.my.service.repository.rating.RatingEntryRepositoryService;
import en.ratings.own.my.service.repository.rating.RatingRepositoryService;
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

    private final UserRepositoryService userRepositoryService;

    private final RatingRepositoryService ratingRepositoryService;

    private final RangeOfValuesRepositoryService rangeOfValuesRepositoryService;

    private final RatingEntryRepositoryService ratingEntryRepositoryService;

    @Autowired
    public RatingService(UserRepositoryService userRepositoryService, RatingRepositoryService ratingRepositoryService,
                         RangeOfValuesRepositoryService rangeOfValuesRepositoryService,
                         RatingEntryRepositoryService ratingEntryRepositoryService) {
        this.userRepositoryService = userRepositoryService;
        this.ratingRepositoryService = ratingRepositoryService;
        this.rangeOfValuesRepositoryService = rangeOfValuesRepositoryService;
        this.ratingEntryRepositoryService = ratingEntryRepositoryService;
    }

    public Rating findById(Long id) throws Exception {
        Optional<Rating> rating = ratingRepositoryService.findById(id);

        if (rating.isEmpty()) {
            throw new RatingByIdNotFoundException(id);
        }
        return rating.get();
    }

    public ArrayList<Rating> findAllByUserId(Long userId) throws Exception {
        Optional<ArrayList<Rating>> ratings = ratingRepositoryService.findAllByUserId(userId);

        if (ratings.isEmpty()) {
            throw new RatingsByUserIdNotFoundException(userId);
        }
        return ratings.get();
    }

    public Rating create(Long userId, RangeOfValues rangeOfValues, String name, String description) throws Exception {
        checkIfRangeOfValuesAndUserIdAreValid(userId, rangeOfValues);
        checkIfRatingNameIsValid(userId, name);
        return createRating(userId, rangeOfValues, name, description);
    }

    public Rating update(Long id, Long userId, RangeOfValues rangeOfValues, String name, String description)
            throws Exception {
        checkIfRangeOfValuesAndRatingIdAndUserIdAreValid(id, userId, rangeOfValues);
        checkIfRatingNameIsValidAndNoInconsistentWithRatingEntries(id, userId, rangeOfValues, name);
        return updateRating(id, userId, rangeOfValues, name, description);
    }

    public void deleteById(Long id) throws Exception {
        Optional<Rating> rating = ratingRepositoryService.findById(id);

        if (rating.isEmpty()) {
            throw new RatingByIdNotFoundException(id);
        }

        Long rangeOfValuesId = rating.get().getRangeOfValuesId();

        ratingEntryRepositoryService.deleteAllByRatingId(id);
        ratingRepositoryService.deleteById(id);
        deleteRangeOfValuesIfNotUsed(rangeOfValuesId);
    }

    private Rating createRating(Long userId, RangeOfValues rangeOfValues, String name, String description)
            throws Exception {
        return updateRating(null, userId, rangeOfValues, name, description);
    }

    private Rating updateRating(Long id, Long userId, RangeOfValues rangeOfValues, String name, String description)
            throws Exception {
        Rating oldRating = findById(id);
        Optional<RangeOfValues> targetRangeOfValues = rangeOfValuesRepositoryService.
                findByMinimumAndMaximumAndStepWidth(rangeOfValues);

        Long newRangeOfValuesId;
        if (targetRangeOfValues.isEmpty()) {
            newRangeOfValuesId = rangeOfValuesRepositoryService.save(rangeOfValues).getId();
        }
        else {
            newRangeOfValuesId = targetRangeOfValues.get().getId();
        }

        Rating newRating = new Rating(userId, newRangeOfValuesId, name, description);
        if (id != null) {
            oldRating.setId(id);
        }

        newRating = ratingRepositoryService.save(newRating);
        deleteRangeOfValuesIfNotUsed(oldRating.getRangeOfValuesId());

        return newRating;
    }

    private void checkIfRangeOfValuesAndUserIdAreValid(Long userId, RangeOfValues rangeOfValues) throws Exception {
        ArrayList<String> keysForException = rangeOfValuesValidation(rangeOfValues);
        keysForException = addExistentStringToArrayList(keysForException, userIdValidation(userId));

        if (!keysForException.isEmpty()) {
            throw new RatingCreationFailedException(keysForException);
        }
    }

    private void checkIfRatingNameIsValid(Long userId, String name) throws Exception {
        ArrayList<String> keysForException = new ArrayList<>();
        addExistentStringToArrayList(keysForException, ratingNameForUserValidation(userId, name));

        if (!keysForException.isEmpty()) {
            throw new RatingCreationFailedException(keysForException);
        }
    }

    private void checkIfRangeOfValuesAndRatingIdAndUserIdAreValid(Long ratingId, Long userId,
                                                                  RangeOfValues rangeOfValues) throws Exception {
        ArrayList<String> keysForException = rangeOfValuesValidation(rangeOfValues);
        keysForException = addExistentStringToArrayList(keysForException, userIdValidation(userId));
        keysForException = addExistentStringToArrayList(keysForException, ratingIdValidation(ratingId));
        if (!keysForException.isEmpty()) {
            throw new RatingUpdateFailedException(keysForException);
        }
    }

    private void checkIfRatingNameIsValidAndNoInconsistentWithRatingEntries
            (Long ratingId, Long userId, RangeOfValues rangeOfValues, String name) throws Exception {
        ArrayList<String> keysForException = new ArrayList<>();
        keysForException = addExistentStringToArrayList(keysForException, ratingNameForUpdateValidation(userId, name));

        ArrayList<String> ratingEntries = ratingEntriesDontFitInNewRangeOfValues(ratingId, rangeOfValues);
        if (!ratingEntries.isEmpty()) {
            keysForException = addExistentStringToArrayList(keysForException, KEY_RATING_ENTRIES_DONT_FIT_IN_SCALE);
        }
        if (!keysForException.isEmpty()) {
            throw new RatingUpdateFailedException(keysForException, ratingEntries);
        }
    }

    private String userIdValidation(Long id) {
        if (userRepositoryService.findById(id).isEmpty()) {
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
        if (ratingRepositoryService.findByUserIdAndName(userId, name).isPresent()) {
            return KEY_RATING_NAME_ALREADY_USED_FOR_USER;
        }
        return null;
    }

    private String ratingIdValidation(Long id) {
        Optional<Rating> rating = ratingRepositoryService.findById(id);

        if (rating.isEmpty()) {
            return KEY_RATING_BY_ID_NOT_FOUND;
        }
        return null;
    }

    private String ratingNameForUpdateValidation(Long userId, String name) {
        return ratingNameForUserValidation(userId, name);
    }

    private ArrayList<String> ratingEntriesDontFitInNewRangeOfValues(Long ratingId, RangeOfValues rangeOfValues) {
        ArrayList<RatingEntry> ratingEntries = ratingEntryRepositoryService.findAllByRatingId(ratingId).get();

        ArrayList<String> ratingEntriesDontFitInScale = new ArrayList<>();

        for (RatingEntry ratingEntry: ratingEntries) {
            if (!isValueInRangeOfValues(ratingEntry.getValue(), rangeOfValues)) {
                ratingEntriesDontFitInScale.add(ratingEntry.getName());
            }
        }
        return ratingEntriesDontFitInScale;
    }

    private void deleteRangeOfValuesIfNotUsed(Long id) {
        if (!ratingRepositoryService.existsByRangeOfValuesId(id)) {
            rangeOfValuesRepositoryService.deleteById(id);
        }
    }

}
