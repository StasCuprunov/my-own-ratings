package en.ratings.own.my.test.integration.controller.rating.entry;

import en.ratings.own.my.AbstractIntegrationTest;
import en.ratings.own.my.controller.rating.RatingController;
import en.ratings.own.my.repository.rating.RangeOfValuesRepository;
import en.ratings.own.my.repository.rating.RatingEntryRepository;
import en.ratings.own.my.repository.rating.RatingRepository;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

public class RatingEntryControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    protected RatingController ratingController;

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
}
