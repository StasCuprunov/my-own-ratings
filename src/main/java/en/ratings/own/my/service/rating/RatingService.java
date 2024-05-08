package en.ratings.own.my.service.rating;

import en.ratings.own.my.dto.rating.RatingDTO;
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

    public RatingDTO findById(String id) throws Exception {
        Rating rating = ratingRepositoryService.findById(id);
        Optional<RangeOfValues> rangeOfValues = rangeOfValuesRepositoryService.findById(rating.getRangeOfValuesId());
        ArrayList<RatingEntry> ratingEntries = ratingEntryRepositoryService.findAllByRatingId(id);

        return new RatingDTO(rating, rangeOfValues.get(), ratingEntries);
    }

    public ArrayList<Rating> findAllByUserId(String userId) {
        return ratingRepositoryService.findAllByUserId(userId);
    }

    public RatingDTO create(RatingDTO ratingDTO) throws Exception {
        String userId = ratingDTO.getUserId();

        checkIfRangeOfValuesAndUserIdAreValid(userId, ratingDTO.getRangeOfValues());
        checkIfRatingNameIsValid(userId, ratingDTO.getName());

        return createRating(ratingDTO);
    }

    public RatingDTO update(RatingDTO ratingDTO) throws Exception {
        String id = ratingDTO.getId();
        String userId = ratingDTO.getUserId();
        RangeOfValues rangeOfValues = ratingDTO.getRangeOfValues();

        checkIfRangeOfValuesAndRatingIdAndUserIdAreValid(id, userId, rangeOfValues);
        checkIfRatingNameIsValidAndNoInconsistentWithRatingEntries(id, userId, rangeOfValues, ratingDTO.getName());

        return updateRating(ratingDTO);
    }

    public void deleteById(String id) throws Exception {
        Rating rating = ratingRepositoryService.findById(id);
        String rangeOfValuesId = rating.getRangeOfValuesId();

        ratingEntryRepositoryService.deleteAllByRatingId(id);
        ratingRepositoryService.deleteById(id);
        deleteRangeOfValuesIfNotUsed(rangeOfValuesId);
    }

    private RatingDTO createRating(RatingDTO ratingDTO) {
        return updateRating(ratingDTO);
    }

    private RatingDTO updateRating(RatingDTO ratingDTO) {
        String ratingId = ratingDTO.getId();
        RangeOfValues newRangeOfValues = ratingDTO.getRangeOfValues();

        String oldRangeOfValuesId = null;

        try {
            Rating oldRating = ratingRepositoryService.findById(ratingId);
            oldRangeOfValuesId = oldRating.getRangeOfValuesId();
        } catch (Exception ignored) {

        }

        Optional<RangeOfValues> targetRangeOfValues = rangeOfValuesRepositoryService.
                findByMinimumAndMaximumAndStepWidth(newRangeOfValues);

        if (targetRangeOfValues.isEmpty()) {
            newRangeOfValues = rangeOfValuesRepositoryService.save(newRangeOfValues);
        }
        else {
            newRangeOfValues = targetRangeOfValues.get();
        }

        Rating newRating = new Rating(ratingDTO.getUserId(), newRangeOfValues.getId(), ratingDTO.getName(),
                ratingDTO.getDescription());

        if (ratingId != null) {
            newRating.setId(ratingId);
        }

        newRating = ratingRepositoryService.save(newRating);

        if (oldRangeOfValuesId != null) {
            deleteRangeOfValuesIfNotUsed(oldRangeOfValuesId);
        }
        return new RatingDTO(newRating, newRangeOfValues);
    }

    private void checkIfRangeOfValuesAndUserIdAreValid(String userId, RangeOfValues rangeOfValues) throws Exception {
        ArrayList<String> keysForException = rangeOfValuesValidation(rangeOfValues);
        keysForException = addExistentStringToArrayList(keysForException, userIdValidation(userId));

        if (!keysForException.isEmpty()) {
            throw new RatingCreationFailedException(keysForException);
        }
    }

    private void checkIfRatingNameIsValid(String userId, String name) throws Exception {
        ArrayList<String> keysForException = new ArrayList<>();
        addExistentStringToArrayList(keysForException, ratingNameForUserValidation(userId, name));

        if (!keysForException.isEmpty()) {
            throw new RatingCreationFailedException(keysForException);
        }
    }

    private void checkIfRangeOfValuesAndRatingIdAndUserIdAreValid(String ratingId, String userId,
                                                                  RangeOfValues rangeOfValues) throws Exception {
        ArrayList<String> keysForException = rangeOfValuesValidation(rangeOfValues);
        keysForException = addExistentStringToArrayList(keysForException, userIdValidation(userId));
        keysForException = addExistentStringToArrayList(keysForException, ratingIdValidation(ratingId));
        if (!keysForException.isEmpty()) {
            throw new RatingUpdateFailedException(keysForException);
        }
    }

    private void checkIfRatingNameIsValidAndNoInconsistentWithRatingEntries
            (String ratingId, String userId, RangeOfValues rangeOfValues, String name) throws Exception {
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

    private String userIdValidation(String id) {
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

    private String ratingNameForUserValidation(String userId, String name) {
        try {
            ratingRepositoryService.findByUserIdAndName(userId, name);
        } catch (Exception e) {
            return null;
        }
        return KEY_RATING_NAME_ALREADY_USED_FOR_USER;
    }

    private String ratingIdValidation(String id) {
        try {
            ratingRepositoryService.findById(id);
        } catch (Exception e) {
            return KEY_RATING_BY_ID_NOT_FOUND;
        }
        return null;
    }

    private String ratingNameForUpdateValidation(String userId, String name) {
        return ratingNameForUserValidation(userId, name);
    }

    private ArrayList<String> ratingEntriesDontFitInNewRangeOfValues(String ratingId, RangeOfValues rangeOfValues) {
        ArrayList<RatingEntry> ratingEntries = ratingEntryRepositoryService.findAllByRatingId(ratingId);

        ArrayList<String> ratingEntriesDontFitInScale = new ArrayList<>();

        for (RatingEntry ratingEntry: ratingEntries) {
            if (!isValueInRangeOfValues(ratingEntry.getValue(), rangeOfValues)) {
                ratingEntriesDontFitInScale.add(ratingEntry.getName());
            }
        }
        return ratingEntriesDontFitInScale;
    }

    private void deleteRangeOfValuesIfNotUsed(String id) {
        if (!ratingRepositoryService.existsByRangeOfValuesId(id)) {
            rangeOfValuesRepositoryService.deleteById(id);
        }
    }

}
