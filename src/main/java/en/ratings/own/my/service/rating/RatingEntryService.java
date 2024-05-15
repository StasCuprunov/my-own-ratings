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
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRY_VALUE_IS_NOT_ALLOWED;
import static en.ratings.own.my.utility.StringUtility.addExistentStringToArrayList;
import static en.ratings.own.my.service.rating.RangeOfValuesValidation.isValueInRangeOfValues;

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
        return update(ratingEntry);
    }

    public RatingEntry update(RatingEntry ratingEntry) throws Exception {
        Rating rating = getRatingForRatingEntryAndCheckRatingEntryId(ratingEntry.getId(), ratingEntry.getRatingId());
        checkIfUpdateIsAllowed(rating.getRangeOfValuesId(), ratingEntry);
        return ratingEntryRepositoryService.save(ratingEntry);
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

    private void checkIfUpdateIsAllowed(String rangeOfValuesId, RatingEntry ratingEntry) throws Exception {
        ArrayList<String> keysForException = new ArrayList<>();

        String ratingEntryNameValidation = ratingEntryNameValidation(ratingEntry.getRatingId(), ratingEntry.getName());
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

    private String ratingEntryNameValidation(String ratingId, String ratingEntryName) {
        if (ratingEntryName.isBlank()) {
            return KEY_RATING_ENTRY_NAME_IS_EMPTY;
        }

        ArrayList<RatingEntry> ratingEntries = ratingEntryRepositoryService.findAllByRatingId(ratingId);

        if (ratingEntries.isEmpty()) {
            return null;
        }

        for (RatingEntry entry: ratingEntries) {
            if (entry.getName().equals(ratingEntryName)) {
                return KEY_RATING_ENTRY_NAME_ALREADY_USED_IN_RATING;
            }
        }
        return null;
    }

    private String ratingEntryValueValidation(String rangeOfValuesId, Double value) throws Exception {
        RangeOfValues rangeOfValues = rangeOfValuesRepositoryService.findById(rangeOfValuesId);

        if (!isValueInRangeOfValues(value, rangeOfValues)) {
            return KEY_RATING_ENTRY_VALUE_IS_NOT_ALLOWED;
        }
        return null;
    }
}
