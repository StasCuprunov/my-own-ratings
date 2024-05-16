package en.ratings.own.my.service.rating;

import en.ratings.own.my.exception.rating.entry.RatingEntryFailedException;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import en.ratings.own.my.service.repository.rating.RangeOfValuesRepositoryService;
import en.ratings.own.my.service.repository.rating.RatingEntryRepositoryService;
import en.ratings.own.my.service.repository.rating.RatingRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_BY_ID_NOT_FOUND;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRY_BY_ID_NOT_FOUND;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRY_NAME_ALREADY_USED_IN_RATING;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRY_NAME_IS_EMPTY;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRY_VALUE_HAS_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRY_VALUE_DOES_NOT_FIT_IN_RANGE_OF_VALUES;
import static en.ratings.own.my.utility.StringUtility.addExistentStringToArrayList;
import static en.ratings.own.my.service.rating.RangeOfValuesValidation.isValueInRangeOfValues;
import static en.ratings.own.my.utility.math.DecimalFormatUtility.numberHasTooManyDecimalDigits;

@Service
public class RatingEntryService {
    private final RatingEntryRepositoryService ratingEntryRepositoryService;

    private final RatingRepositoryService ratingRepositoryService;

    private final RangeOfValuesRepositoryService rangeOfValuesRepositoryService;

    @Autowired
    public RatingEntryService(RatingEntryRepositoryService ratingEntryRepositoryService,
                              RatingRepositoryService ratingRepositoryService,
                              RangeOfValuesRepositoryService rangeOfValuesRepositoryService) {
        this.ratingEntryRepositoryService = ratingEntryRepositoryService;
        this.ratingRepositoryService = ratingRepositoryService;
        this.rangeOfValuesRepositoryService = rangeOfValuesRepositoryService;
    }

    public ArrayList<RatingEntry> findAllByRatingId(String ratingId) {
        return ratingEntryRepositoryService.findAllByRatingId(ratingId);
    }

    public RatingEntry create(RatingEntry ratingEntry) throws Exception {
        return save(ratingEntry);
    }

    public RatingEntry update(RatingEntry ratingEntry) throws Exception {
        return save(ratingEntry);
    }

    public RatingEntry save(RatingEntry ratingEntry) throws Exception {
        Rating rating = getRatingForRatingEntryAndCheckRatingEntryId(ratingEntry.getId(), ratingEntry.getRatingId());
        checkIfSaveIsAllowed(rating.getRangeOfValuesId(), ratingEntry);
        return saveRatingEntry(ratingEntry);
    }

    public void deleteById(String id) {
        ratingEntryRepositoryService.deleteById(id);
    }

    public void deleteAllByRatingId(String ratingId) {
        ratingEntryRepositoryService.deleteAllByRatingId(ratingId);
    }

    private Rating getRatingForRatingEntryAndCheckRatingEntryId(String ratingEntryId, String ratingId) throws Exception {
        ArrayList<String> keysForException = new ArrayList<>();
        if (ratingEntryId != null) {
            addExistentStringToArrayList(keysForException, idValidation(ratingEntryId));
        }
        Rating rating = null;
        try {
            rating = ratingRepositoryService.findById(ratingId);
        } catch (Exception e) {
            keysForException.add(KEY_RATING_BY_ID_NOT_FOUND);
        }

        if (!keysForException.isEmpty()) {
            throw new RatingEntryFailedException(keysForException);
        }
        return rating;
    }

    private String idValidation(String id) {
        try {
            ratingEntryRepositoryService.findById(id);
        } catch (Exception e) {
            return KEY_RATING_ENTRY_BY_ID_NOT_FOUND;
        }
        return null;
    }

    private void checkIfSaveIsAllowed(String rangeOfValuesId, RatingEntry ratingEntry) throws Exception {
        ArrayList<String> keysForException = new ArrayList<>();
        String ratingEntryNameValidation = ratingEntryNameValidation(ratingEntry);


        if (ratingEntryNameValidation != null) {
            keysForException.add(ratingEntryNameValidation);
        }

        String ratingEntryValueValidation = ratingEntryValueValidation(rangeOfValuesId, ratingEntry.getValue());
        if (ratingEntryValueValidation != null) {
            keysForException.add(ratingEntryValueValidation);
        }

        if (!keysForException.isEmpty()) {
            throw new RatingEntryFailedException(keysForException);
        }
    }

    private String ratingEntryNameValidation(RatingEntry ratingEntry) {
        String name = ratingEntry.getName();
        if (name.isBlank()) {
            return KEY_RATING_ENTRY_NAME_IS_EMPTY;
        }

        ArrayList<RatingEntry> ratingEntries = ratingEntryRepositoryService.
                findAllByRatingId(ratingEntry.getRatingId());

        if (ratingEntries.isEmpty()) {
            return null;
        }
        String id = ratingEntry.getId();
        for (RatingEntry entry: ratingEntries) {
            if (isNameAlreadyUsedInDifferentRatingEntry(id, name, entry)) {
                return KEY_RATING_ENTRY_NAME_ALREADY_USED_IN_RATING;
            }
        }
        return null;
    }

    private boolean isNameAlreadyUsedInDifferentRatingEntry(String id, String name, RatingEntry differentRatingEntry) {
        if (!differentRatingEntry.getName().equals(name)) {
            return false;
        }
        if (id != null) {
            return !id.equals(differentRatingEntry.getId());
        }
        return true;
    }

    private String ratingEntryValueValidation(String rangeOfValuesId, Double value) throws Exception {
        if (numberHasTooManyDecimalDigits(value)) {
            return KEY_RATING_ENTRY_VALUE_HAS_TOO_MANY_DECIMAL_DIGITS;
        }
        RangeOfValues rangeOfValues = rangeOfValuesRepositoryService.findById(rangeOfValuesId);
        if (!isValueInRangeOfValues(value, rangeOfValues)) {
            return KEY_RATING_ENTRY_VALUE_DOES_NOT_FIT_IN_RANGE_OF_VALUES;
        }
        return null;
    }

    private RatingEntry saveRatingEntry(RatingEntry ratingEntry) {
        return ratingEntryRepositoryService.save(ratingEntry);
    }
}
