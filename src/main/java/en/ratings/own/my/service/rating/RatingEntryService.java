package en.ratings.own.my.service.rating;

import en.ratings.own.my.exception.rating.RatingByIdNotFoundException;
import en.ratings.own.my.exception.rating.entry.RatingEntriesByRatingIdNotFoundException;
import en.ratings.own.my.exception.rating.entry.RatingEntryByIdNotFoundException;
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
import java.util.Optional;

import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRY_NAME_ALREADY_USED_IN_RATING;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRY_VALUE_IS_NOT_ALLOWED;
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

    public RatingEntry findById(Long id) throws Exception {
        Optional<RatingEntry> ratingEntry = ratingEntryRepositoryService.findById(id);

        if (ratingEntry.isEmpty()) {
            throw new RatingEntryByIdNotFoundException(id);
        }
        return ratingEntry.get();
    }

    public ArrayList<RatingEntry> findAllByRatingId(Long ratingId) throws Exception {
        Optional<ArrayList<RatingEntry>> ratingEntries = ratingEntryRepositoryService.findAllByRatingId(ratingId);

        if (ratingEntries.isEmpty()) {
            throw new RatingEntriesByRatingIdNotFoundException(ratingId);
        }
        return ratingEntries.get();
    }

    public RatingEntry create(Long ratingId, String name, Double value) throws Exception {
        return update(null, ratingId, name, value);
    }

    public RatingEntry update(Long id, Long ratingId, String name, Double value) throws Exception {
        if (id != null) {
            findById(id);
        }

        Optional<Rating> rating = ratingRepositoryService.findById(ratingId);
        if (rating.isEmpty()) {
            throw new RatingByIdNotFoundException(ratingId);
        }

        RatingEntry ratingEntry = new RatingEntry(ratingId, name, value);
        checkIfUpdateIsAllowed(rating.get().getRangeOfValuesId(), ratingEntry);

        if (id != null) {
            ratingEntry.setId(id);
        }
        return ratingEntryRepositoryService.save(ratingEntry);
    }

    public void deleteById(Long id) {
        ratingEntryRepositoryService.deleteById(id);
    }

    public void deleteAllByRatingId(Long ratingId) {
        ratingEntryRepositoryService.deleteAllByRatingId(ratingId);
    }

    private void checkIfUpdateIsAllowed(Long rangeOfValuesId, RatingEntry ratingEntry) throws Exception {
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

    private String ratingEntryNameValidation(Long ratingId, String ratingEntryName) {
        Optional<ArrayList<RatingEntry>> ratingEntries = ratingEntryRepositoryService.findAllByRatingId(ratingId);

        if (ratingEntries.isEmpty()) {
            return null;
        }

        for (RatingEntry entry: ratingEntries.get()) {
            if (entry.getName().equals(ratingEntryName)) {
                return KEY_RATING_ENTRY_NAME_ALREADY_USED_IN_RATING;
            }
        }
        return null;
    }

    private String ratingEntryValueValidation(Long rangeOfValuesId, Double value) {
        RangeOfValues rangeOfValues = rangeOfValuesRepositoryService.findById(rangeOfValuesId).get();

        if (!isValueInRangeOfValues(value, rangeOfValues)) {
            return KEY_RATING_ENTRY_VALUE_IS_NOT_ALLOWED;
        }
        return null;
    }
}
