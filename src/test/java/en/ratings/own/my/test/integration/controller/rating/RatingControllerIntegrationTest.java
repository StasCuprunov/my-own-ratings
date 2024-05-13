package en.ratings.own.my.test.integration.controller.rating;

import en.ratings.own.my.test.integration.AbstractIntegrationTest;
import en.ratings.own.my.controller.rating.RatingController;
import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.User;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import en.ratings.own.my.repository.rating.RangeOfValuesRepository;
import en.ratings.own.my.repository.rating.RatingEntryRepository;
import en.ratings.own.my.repository.rating.RatingRepository;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static en.ratings.own.my.test.constant.TestConstants.EXPECTED_ONE;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingEntryAppleJuiceForDrinksWithNegativeMinimum;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingEntryCokeForDrinksWithNegativeMinimum;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingEntryRedBullForDrinksWithNegativeMinimum;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RatingControllerIntegrationTest extends AbstractIntegrationTest {

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

    protected ResponseEntity<RatingDTO> createValidRating(User user, RatingDTO input) {
        return createValidRating(user, input, null);
    }

    protected ResponseEntity<RatingDTO> createValidRating(User user, RatingDTO input, String differentUserId) {
        login(user);
        String id = (differentUserId == null) ? user.getId() : differentUserId;
        input.setUserId(id);
        ResponseEntity<RatingDTO> responseEntity = null;
        try {
            responseEntity = ratingController.create(input);
        } catch (Exception ignored) {

        }
        return responseEntity;
    }

    protected void createRatingEntriesForDrinksWithNegativeMinimum(String ratingId) {
        saveRatingEntryRepository(createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId));
        saveRatingEntryRepository(createValidRatingEntryAppleJuiceForDrinksWithNegativeMinimum(ratingId));
        saveRatingEntryRepository(createValidRatingEntryRedBullForDrinksWithNegativeMinimum(ratingId));
    }

    protected void compareIfRangeOfValuesExistsExactOnce(RatingDTO input) {
        RangeOfValues inputRangeOfValues = input.getRangeOfValues();
        ArrayList<RangeOfValues> listOfRangeOfValues =  findAllRangeOfValuesRepository();

        int listSize = listOfRangeOfValues.size();
        int numberOfFoundRangeOfValues = 0;
        for (int index = 0; index < listSize; index++) {
            if (listOfRangeOfValues.get(index).equalsWithoutId(inputRangeOfValues)) {
                numberOfFoundRangeOfValues++;
            }
        }
        assertThat(numberOfFoundRangeOfValues).isEqualTo(EXPECTED_ONE);
    }

    protected Optional<Rating> findByIdRatingRepository(String id) {
        return ratingRepository.findById(id);
    }

    protected ArrayList<Rating> findAllRatingRepository() {
        return ratingRepository.findAll();
    }

    protected ArrayList<RangeOfValues> findAllRangeOfValuesRepository() {
        return rangeOfValuesRepository.findAll();
    }

    protected Optional<RangeOfValues> findByIdRangeOfValuesRepository(String id) {
        return rangeOfValuesRepository.findById(id);
    }

    protected Optional<RangeOfValues> findByMinimumAndMaximumAndStepWidthRangeOfValuesRepository(Double minimum,
                                                                                                Double maximum,
                                                                                                Double stepWidth) {
        return rangeOfValuesRepository.findByMinimumAndMaximumAndStepWidth(minimum, maximum, stepWidth);
    }

    protected Optional<RangeOfValues> findByMinimumAndMaximumAndStepWidthRangeOfValuesRepository
            (RangeOfValues rangeOfValues) {
        return findByMinimumAndMaximumAndStepWidthRangeOfValuesRepository(rangeOfValues.getMinimum(),
                rangeOfValues.getMaximum(), rangeOfValues.getStepWidth());
    }

    protected void saveRatingEntryRepository(RatingEntry ratingEntry) {
        ratingEntryRepository.save(ratingEntry);
    }

    protected ArrayList<RatingEntry> findAllRatingEntryRepository() {
        return ratingEntryRepository.findAll();
    }

    protected ArrayList<RatingEntry> findAllByRatingIdRatingEntryRepository(String ratingId) {
        return ratingEntryRepository.findAllByRatingId(ratingId);
    }
}
