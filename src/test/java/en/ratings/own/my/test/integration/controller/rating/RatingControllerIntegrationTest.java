package en.ratings.own.my.test.integration.controller.rating;

import en.ratings.own.my.AbstractIntegrationTest;
import en.ratings.own.my.controller.AuthenticationController;
import en.ratings.own.my.controller.UserController;
import en.ratings.own.my.controller.rating.RatingController;
import en.ratings.own.my.dto.LoginDTO;
import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.User;
import en.ratings.own.my.repository.UserRepository;
import en.ratings.own.my.repository.rating.RangeOfValuesRepository;
import en.ratings.own.my.repository.rating.RatingEntryRepository;
import en.ratings.own.my.repository.rating.RatingRepository;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static en.ratings.own.my.test.integration.utility.CreateUserUtility.createUserFalakNoorahKhoury;
import static en.ratings.own.my.test.integration.utility.CreateUserUtility.createUserStevenWorm;
import static en.ratings.own.my.test.integration.utility.HttpResponseUtility.createHttpServletResponse;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        createValidRatingEntryAppleJuiceForDrinksWithNegativeMinimum;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        createValidRatingEntryCokeForDrinksWithNegativeMinimum;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        createValidRatingEntryRedBullForDrinksWithNegativeMinimum;
import static org.springframework.security.core.context.SecurityContextHolder.clearContext;

public class RatingControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    protected AuthenticationController authenticationController;

    @Autowired
    protected RatingController ratingController;

    @Autowired
    protected UserController userController;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RatingRepository ratingRepository;

    @Autowired
    protected RangeOfValuesRepository rangeOfValuesRepository;

    @Autowired
    protected RatingEntryRepository ratingEntryRepository;

    protected User userStevenWorm;

    protected User userFalakNoorahKhoury;

    protected final int MAX_RANGE_OF_VALUES_WITH_SAME_ATTRIBUTES_IN_DOCUMENT = 1;

    protected final int NUMBER_OF_RATING_ENTRIES_AFTER_CREATE_RATING = 0;

    protected final int NUMBER_OF_UNIQUE_RATING_ENTRIES = 1;

    @Before
    public void setup() {
        userStevenWorm = storeUserInDatabase(createUserStevenWorm());
        userFalakNoorahKhoury = storeUserInDatabase(createUserFalakNoorahKhoury());
    }

    @After
    public void clean() {
        userRepository.deleteAll();
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
        ratingEntryRepository.save(createValidRatingEntryCokeForDrinksWithNegativeMinimum(ratingId));
        ratingEntryRepository.save(createValidRatingEntryAppleJuiceForDrinksWithNegativeMinimum(ratingId));
        ratingEntryRepository.save(createValidRatingEntryRedBullForDrinksWithNegativeMinimum(ratingId));
    }

    protected void login(User user) {
        try {
            authenticationController.login(new LoginDTO(user.getEmail(), user.getPassword()),
                    createHttpServletResponse());
        } catch (Exception e) {

        }
    }

    protected void logout() {
        clearContext();
    }

    protected User storeUserInDatabase(User user) {
        String rawPassword = user.getPassword();
        try {
            userController.create(user);
        } catch (Exception ignored) {

        }
        user.setPassword(rawPassword);
        return user;
    }

}