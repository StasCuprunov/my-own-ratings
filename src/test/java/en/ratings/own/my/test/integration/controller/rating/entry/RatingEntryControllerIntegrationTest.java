package en.ratings.own.my.test.integration.controller.rating.entry;

import en.ratings.own.my.AbstractIntegrationTest;
import en.ratings.own.my.controller.rating.RatingEntryController;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import en.ratings.own.my.repository.rating.RangeOfValuesRepository;
import en.ratings.own.my.repository.rating.RatingEntryRepository;
import en.ratings.own.my.repository.rating.RatingRepository;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RatingEntryControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    protected RatingEntryController ratingEntryController;

    @Autowired
    protected RatingRepository ratingRepository;

    @Autowired
    protected RangeOfValuesRepository rangeOfValuesRepository;

    @Autowired
    protected RatingEntryRepository ratingEntryRepository;

    @After
    public void clean() {
        deleteAllUserRepository();
        ratingRepository.deleteAll();
        rangeOfValuesRepository.deleteAll();
        ratingEntryRepository.deleteAll();
    }

    protected Optional<RatingEntry> findByIdRatingEntryRepository(String id) {
        return ratingEntryRepository.findById(id);
    }

    protected Optional<Rating> findByIdRatingRepository(String id) {
        return ratingRepository.findById(id);
    }

    protected Optional<RangeOfValues> findByIdRangeOfValuesRepository(String id) {
        return rangeOfValuesRepository.findById(id);
    }
}
