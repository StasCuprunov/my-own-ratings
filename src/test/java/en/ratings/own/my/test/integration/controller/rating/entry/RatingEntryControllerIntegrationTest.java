package en.ratings.own.my.test.integration.controller.rating.entry;

import en.ratings.own.my.test.integration.AbstractIntegrationTest;
import en.ratings.own.my.controller.rating.RatingController;
import en.ratings.own.my.controller.rating.RatingEntryController;
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

import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingDTODrinkInAsiaWithAmazonRating;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingDTODrinksWithNegativeMinimum;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingEntryCokeForDrinksWithNegativeMinimum;

public class RatingEntryControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    protected RatingController ratingController;

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

    protected ArrayList<RatingEntry> findAllByRatingIdRatingEntryRepository(String ratingId) {
        return ratingEntryRepository.findAllByRatingId(ratingId);
    }

    protected ArrayList<RatingEntry> findAllRatingEntryRepository() {
        return ratingEntryRepository.findAll();
    }

    protected RatingEntry saveRatingEntryRepository(RatingEntry ratingEntry) {
        return ratingEntryRepository.save(ratingEntry);
    }

    protected Optional<Rating> findByIdRatingRepository(String id) {
        return ratingRepository.findById(id);
    }

    protected Optional<RangeOfValues> findByIdRangeOfValuesRepository(String id) {
        return rangeOfValuesRepository.findById(id);
    }

    protected ResponseEntity<RatingDTO> createRatingBooksWithGermanGrading(User user) {
        return createValidRating(user, VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING);
    }

    protected ResponseEntity<RatingDTO> createRatingBooksWithAmazonRating(User user) {
        return createValidRating(user, VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING);
    }

    protected ResponseEntity<RatingDTO> createRatingDrinksWithNegativeMinimum(User user) {
        return createValidRating(user, createValidRatingDTODrinksWithNegativeMinimum());
    }

    protected ResponseEntity<RatingDTO> createValidRatingDrinkInAsiaWithAmazonRating(User user) {
        return createValidRating(user, createValidRatingDTODrinkInAsiaWithAmazonRating());
    }

    protected RatingEntry saveValidRatingEntryCokeForDrinksWithNegativeMinimum(String ratingId) {
        return saveRatingEntryRepository(createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId));
    }

    private ResponseEntity<RatingDTO> createValidRating(User user, RatingDTO input) {
        input.setUserId(user.getId());
        login(user);
        try {
            return ratingController.create(input);
        } catch (Exception ignored) {

        }
        return null;
    }

    protected RatingEntry createNewRatingEntryObject(RatingEntry ratingEntry) {
        return new RatingEntry(ratingEntry.getRatingId(), ratingEntry.getRatingId(), ratingEntry.getName(),
                ratingEntry.getValue());
    }
}
