package en.ratings.own.my.service.rating;

import en.ratings.own.my.dto.rating.ShowRatingDTO;
import en.ratings.own.my.dto.rating.form.EditRatingDTO;
import en.ratings.own.my.dto.rating.form.RatingValidationDTO;
import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.exception.rating.creation.RatingCreationFailedException;
import en.ratings.own.my.exception.rating.update.RatingUpdateFailedException;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import en.ratings.own.my.service.authentication.AuthenticationService;
import en.ratings.own.my.service.repository.UserRepositoryService;
import en.ratings.own.my.service.repository.rating.RangeOfValuesRepositoryService;
import en.ratings.own.my.service.repository.rating.RatingEntryRepositoryService;
import en.ratings.own.my.service.repository.rating.RatingRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_DESCRIPTION_TOO_LONG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRIES_DONT_FIT_IN_SCALE;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_HAS_DEFINED_ID;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_NAME_IS_EMPTY;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_NAME_IS_TOO_LONG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_USER_WITH_ID_NOT_FOUND;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_NAME_ALREADY_USED_FOR_USER;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_BY_ID_NOT_FOUND;
import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_DESCRIPTION;
import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_NAME;
import static en.ratings.own.my.service.rating.RangeOfValuesValidation.isValueInRangeOfValues;
import static en.ratings.own.my.service.rating.RangeOfValuesValidation.rangeOfValuesValidation;
import static en.ratings.own.my.utility.StringUtility.addExistentStringToArrayList;
import static en.ratings.own.my.utility.StringUtility.isStringTooLong;

@Service
public class RatingService {

    private final UserRepositoryService userRepositoryService;

    private final RatingRepositoryService ratingRepositoryService;

    private final RangeOfValuesRepositoryService rangeOfValuesRepositoryService;

    private final RatingEntryRepositoryService ratingEntryRepositoryService;

    private final AuthenticationService authenticationService;

    @Autowired
    public RatingService(UserRepositoryService userRepositoryService, RatingRepositoryService ratingRepositoryService,
                         RangeOfValuesRepositoryService rangeOfValuesRepositoryService,
                         RatingEntryRepositoryService ratingEntryRepositoryService,
                         AuthenticationService authenticationService) {
        this.userRepositoryService = userRepositoryService;
        this.ratingRepositoryService = ratingRepositoryService;
        this.rangeOfValuesRepositoryService = rangeOfValuesRepositoryService;
        this.ratingEntryRepositoryService = ratingEntryRepositoryService;
        this.authenticationService = authenticationService;
    }

    public ShowRatingDTO findById(String id) throws Exception {
        Rating rating = ratingRepositoryService.findById(id);
        RangeOfValues rangeOfValues = rangeOfValuesRepositoryService.findById(rating.getRangeOfValuesId());
        ArrayList<RatingEntry> ratingEntries = ratingEntryRepositoryService.findAllByRatingId(id);

        return new ShowRatingDTO(rating, rangeOfValues, ratingEntries);
    }

    public RatingDTO findByIdWithoutRatingEntries(String id) throws Exception {
        Rating rating = ratingRepositoryService.findById(id);
        RangeOfValues rangeOfValues = rangeOfValuesRepositoryService.findById(rating.getRangeOfValuesId());

        return new RatingDTO(rating, rangeOfValues);
    }

    public ArrayList<Rating> findAllByUserId(String userId) {
        return ratingRepositoryService.findAllByUserId(userId);
    }

    public RatingDTO create(RatingDTO ratingDTO) throws Exception {
        checkIfIdAndRangeOfValuesAndUserIdAreValid(ratingDTO);
        checkIfRatingNameAndDescriptionAreValid(ratingDTO);

        return createRating(ratingDTO);
    }

    public RatingValidationDTO getInfoForCreate() throws Exception {
        return new RatingValidationDTO(findActualUserId());
    }

    public RatingDTO update(RatingDTO ratingDTO) throws Exception {
        String id = ratingDTO.getId();
        String userId = ratingDTO.getUserId();
        RangeOfValues rangeOfValues = ratingDTO.getRangeOfValues();

        checkIfRangeOfValuesAndRatingIdAndUserIdAreValid(id, userId, rangeOfValues);
        checkIfRatingNameAndDescriptionAreValidAndNoInconsistentWithRatingEntries(ratingDTO);

        return updateRating(ratingDTO);
    }

    public EditRatingDTO getInfoForEdit(String id) throws Exception {
        RatingDTO ratingDTO = findByIdWithoutRatingEntries(id);
        return new EditRatingDTO(findActualUserId(), ratingDTO);
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

        try {
            newRangeOfValues = rangeOfValuesRepositoryService.findByMinimumAndMaximumAndStepWidth(newRangeOfValues);
        } catch (Exception e) {
            newRangeOfValues = rangeOfValuesRepositoryService.save(newRangeOfValues);
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

    private void checkIfIdAndRangeOfValuesAndUserIdAreValid(RatingDTO ratingDTO) throws Exception {
        ArrayList<String> keysForException = rangeOfValuesValidation(ratingDTO.getRangeOfValues());
        keysForException = addExistentStringToArrayList(keysForException, userIdValidation(ratingDTO.getUserId()));
        keysForException = addExistentStringToArrayList(keysForException, idValidationForCreate(ratingDTO.getId()));

        if (!keysForException.isEmpty()) {
            throw new RatingCreationFailedException(keysForException);
        }
    }

    private void checkIfRatingNameAndDescriptionAreValid(RatingDTO ratingDTO) throws Exception {
        ArrayList<String> keysForException = new ArrayList<>();
        addExistentStringToArrayList(keysForException, ratingNameValidationForCreate(ratingDTO.getUserId(),
                ratingDTO.getName()));
        addExistentStringToArrayList(keysForException, descriptionValidation(ratingDTO.getDescription()));

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

    private void checkIfRatingNameAndDescriptionAreValidAndNoInconsistentWithRatingEntries
            (RatingDTO ratingDTO) throws Exception {
        String ratingId = ratingDTO.getId();

        ArrayList<String> keysForException = new ArrayList<>();
        keysForException = addExistentStringToArrayList(keysForException,
                ratingNameForUpdateValidation(ratingId, ratingDTO.getUserId(), ratingDTO.getName()));
        keysForException = addExistentStringToArrayList(keysForException,
                descriptionValidation(ratingDTO.getDescription()));

        ArrayList<String> ratingEntries = ratingEntriesDontFitInNewRangeOfValues(ratingId, ratingDTO.getRangeOfValues());
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

    private String ratingNameValidationForCreate(String userId, String name) {
        return ratingNameValidation(null, userId, name);
    }

    private String idValidationForCreate(String id) {
        if (id != null) {
            return KEY_RATING_HAS_DEFINED_ID;
        }
        return null;
    }

    private String ratingIdValidation(String id) {
        try {
            ratingRepositoryService.findById(id);
        } catch (Exception e) {
            return KEY_RATING_BY_ID_NOT_FOUND;
        }
        return null;
    }

    private String ratingNameForUpdateValidation(String ratingId, String userId, String name) {
        return ratingNameValidation(ratingId, userId, name);
    }

    private String ratingNameValidation(String ratingId, String userId, String name) {
        if (name.isBlank()) {
            return KEY_RATING_NAME_IS_EMPTY;
        }
        else if (isRatingNameTooLong(name)) {
            return KEY_RATING_NAME_IS_TOO_LONG;
        }

        Rating rating;
        try {
            rating = ratingRepositoryService.findByUserIdAndName(userId, name);
        } catch (Exception e) {
            return null;
        }
        if (!rating.getId().equals(ratingId)) {
            return KEY_RATING_NAME_ALREADY_USED_FOR_USER;
        }
        return null;
    }

    private boolean isRatingNameTooLong(String ratingName) {
        return isStringTooLong(ratingName, MAX_LENGTH_OF_NAME);
    }

    private String descriptionValidation(String description) {
        if (isDescriptionTooLong(description)) {
            return KEY_RATING_DESCRIPTION_TOO_LONG;
        }
        return null;
    }

    private boolean isDescriptionTooLong(String description) {
        return isStringTooLong(description, MAX_LENGTH_OF_DESCRIPTION);
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

    private String findActualUserId() throws Exception {
        String email = authenticationService.getAuthentication().getName();
        return userRepositoryService.findByEmail(email).getId();
    }

}
