package en.ratings.own.my.test.integration.controller.rating;

import en.ratings.own.my.AbstractIntegrationTest;
import en.ratings.own.my.controller.AuthenticationController;
import en.ratings.own.my.controller.UserController;
import en.ratings.own.my.controller.rating.RatingController;
import en.ratings.own.my.dto.LoginDTO;
import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.User;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import en.ratings.own.my.repository.UserRepository;
import en.ratings.own.my.repository.rating.RangeOfValuesRepository;
import en.ratings.own.my.repository.rating.RatingEntryRepository;
import en.ratings.own.my.repository.rating.RatingRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static en.ratings.own.my.AssertThatUtility.
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException;
import static en.ratings.own.my.AssertThatUtility.assertThatId;
import static en.ratings.own.my.AssertThatUtility.assertThatStatusCodeIsCreated;
import static en.ratings.own.my.AssertThatUtility.assertThatExceptionIsEqualToRatingCreationFailedException;
import static en.ratings.own.my.test.integration.controller.utility.CreateUserUtility.createUserFalakNoorahKhoury;
import static en.ratings.own.my.test.integration.controller.utility.CreateUserUtility.createUserStevenWorm;
import static en.ratings.own.my.test.integration.controller.utility.HttpResponseUtility.createHttpServletResponse;
import static en.ratings.own.my.test.integration.controller.utility.rating.CreateRatingDTOUtility.
        INVALID_RATING_DTO_BECAUSE_EMPTY_NAME;
import static en.ratings.own.my.test.integration.controller.utility.rating.RatingBooksUtility.
        INVALID_RATING_DTO_BOOKS_WITH_NEGATIVE_STEP_WIDTH;
import static en.ratings.own.my.test.integration.controller.utility.rating.RatingBooksUtility.
        INVALID_RATING_DTO_BOOKS_WITH_ZERO_STEP_WIDTH;
import static en.ratings.own.my.test.integration.controller.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING;
import static en.ratings.own.my.test.integration.controller.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.integration.controller.utility.rating.RatingBooksUtility.
        createValidRatingDTOBooksWithGermanGradingAndDefinedRatingEntries;
import static en.ratings.own.my.test.integration.controller.utility.rating.RatingDrinksUtility.
        INVALID_RATING_DTO_DRINKS_WITH_MINIMUM_EQUALS_TO_MAXIMUM;
import static en.ratings.own.my.test.integration.controller.utility.rating.RatingDrinksUtility.
        INVALID_RATING_DTO_DRINKS_WITH_MINIMUM_GREATER_THAN_MAXIMUM;
import static en.ratings.own.my.test.integration.controller.utility.rating.RatingDrinksUtility.
        INVALID_RATING_DTO_DRINKS_WITH_UNAVAILABLE_MAXIMUM;
import static en.ratings.own.my.test.integration.controller.utility.rating.RatingDrinksUtility.
        INVALID_RATING_DTO_WITH_EMPTY_NAME_AND_NO_DESCRIPTION_AND_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.integration.controller.utility.rating.RatingDrinksUtility.
        VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM;
import static en.ratings.own.my.test.integration.controller.utility.rating.RatingDrinksUtility.
        VALID_RATING_DTO_DRINKS_WITH_NO_DESCRIPTION_AND_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.security.core.context.SecurityContextHolder.clearContext;

public class RatingControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private RatingController ratingController;

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RangeOfValuesRepository rangeOfValuesRepository;

    @Autowired
    private RatingEntryRepository ratingEntryRepository;

    private User userStevenWorm;

    private User userFalakNoorahKhoury;

    private final int MAX_RANGE_OF_VALUES_WITH_SAME_ATTRIBUTES_IN_DOCUMENT = 1;

    private final int NUMBER_OF_RATING_ENTRIES_AFTER_CREATE_RATING = 0;

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

    @Test
    public void testValidCreate() {
        testValidCreate(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING);
    }

    @Test
    public void testValidCreateWithDefinedId() {
        RatingDTO input = VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
        input.setId("test");
        testValidCreate(userFalakNoorahKhoury, input);
    }

    @Test
    public void testValidCreateWithDefinedRatingEntries() {
        testValidCreate(userStevenWorm, createValidRatingDTOBooksWithGermanGradingAndDefinedRatingEntries());
    }

    @Test
    public void testValidCreateWithSameInputsButDifferentUsers() {
        RatingDTO input = VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
        testValidCreate(userStevenWorm, input);
        testValidCreate(userFalakNoorahKhoury, input);
    }

    @Test
    public void testValidCreateWithSameRatingNamesButDifferentUsers() {
        testValidCreate(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING);
        testValidCreate(userFalakNoorahKhoury, VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING);
    }

    @Test
    public void testValidCreateWithNegativeMinimum() {
        testValidCreate(userFalakNoorahKhoury, VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM);
    }

    @Test
    public void testValidCreateWithNoDescriptionAndWithNegativeMinimumAndMaximum() {
        testValidCreate(userStevenWorm,
                VALID_RATING_DTO_DRINKS_WITH_NO_DESCRIPTION_AND_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM);
    }

    @Test
    public void testInvalidCreateWithoutLoggedIn() {
        logout();
        RatingDTO input = VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING;
        input.setUserId(userStevenWorm.getId());
        Exception foundException  = new Exception();
        try {
            ratingController.create(input);
        } catch (Exception e) {
            foundException = e;
        }
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(foundException);
    }

    @Test
    public void testInvalidCreateWithEmptyName() {
        testInvalidCreate(userStevenWorm, INVALID_RATING_DTO_BECAUSE_EMPTY_NAME);
    }

    @Test
    public void testInvalidCreateWithNegativeStepWidth() {
        testInvalidCreate(userFalakNoorahKhoury, INVALID_RATING_DTO_BOOKS_WITH_NEGATIVE_STEP_WIDTH);
    }

    @Test
    public void testInvalidCreateWithZeroStepWidth() {
        testInvalidCreate(userFalakNoorahKhoury, INVALID_RATING_DTO_BOOKS_WITH_ZERO_STEP_WIDTH);
    }

    @Test
    public void testInvalidCreateWithEmptyNameAndNoDescription() {
        testInvalidCreate(userStevenWorm, INVALID_RATING_DTO_WITH_EMPTY_NAME_AND_NO_DESCRIPTION_AND_WITH_GERMAN_GRADING);
    }

    @Test
    public void testInvalidCreateWithMinimumEqualsToMaximum() {
        testInvalidCreate(userStevenWorm, INVALID_RATING_DTO_DRINKS_WITH_MINIMUM_EQUALS_TO_MAXIMUM);
    }

    @Test
    public void testInvalidCreateWithMinimumGreaterThanMaximum() {
        testInvalidCreate(userFalakNoorahKhoury, INVALID_RATING_DTO_DRINKS_WITH_MINIMUM_GREATER_THAN_MAXIMUM);
    }

    @Test
    public void testInvalidCreateWithUnavailableMaximum() {
        testInvalidCreate(userStevenWorm, INVALID_RATING_DTO_DRINKS_WITH_UNAVAILABLE_MAXIMUM);
    }

    @Test
    public void testInvalidCreateWithForeignUserId() {
        testInvalidCreate(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING, userFalakNoorahKhoury.getId());
    }

    private void testValidCreate(User user, RatingDTO input) {
        testValidCreate(user, input, null);
    }

    private void testValidCreate(User user, RatingDTO input, String differentUserId) {
        login(user);
        String id = (differentUserId == null) ? user.getId() : differentUserId;
        input.setUserId(id);
        ResponseEntity<RatingDTO> responseEntity = null;
        try {
            responseEntity = ratingController.create(input);
        } catch (Exception ignored) {

        }
        testValidCreate(input, responseEntity);
    }

    private void testInvalidCreate(User user, RatingDTO input, String differentUserId) {
        login(user);
        String id = (differentUserId == null) ? user.getId() : differentUserId;
        input.setUserId(id);
        Exception foundException  = new Exception();
        try {
            ratingController.create(input);
        } catch (Exception e) {
            foundException = e;
        }
        assertThatExceptionIsEqualToRatingCreationFailedException(foundException);
    }
    private void testInvalidCreate(User user, RatingDTO input) {
        testInvalidCreate(user, input, null);
    }

    private void testValidCreate(RatingDTO input, ResponseEntity<RatingDTO> responseEntity) {
        RatingDTO result = responseEntity.getBody();
        assertAll("Test valid create rating",
                () -> assertThatStatusCodeIsCreated(responseEntity),
                () -> compareInputWithResultAfterValidCreate(input, result),
                () -> compareResultWithDatabaseAfterValidCreate(result)
        );
    }

    private void compareInputWithResultAfterValidCreate(RatingDTO input, RatingDTO result) {
        assertThatId(result.getUserId());
        assertThat(input.equalsAfterCreate(result)).isTrue();
    }

    private void compareResultWithDatabaseAfterValidCreate(RatingDTO input) {
        Rating rating = compareInputWithRatingDocumentAfterCreate(input);
        compareInputWithRangeOfValuesDocumentAfterCreate(input, rating);
        compareInputRatingEntryDocumentAfterCreate(input);
    }

    private Rating compareInputWithRatingDocumentAfterCreate(RatingDTO input) {
        Rating rating = ratingRepository.findById(input.getId()).get();

        assertThat(input.getUserId()).isEqualTo(rating.getUserId());
        assertThat(input.getName()).isEqualTo(rating.getName());
        assertThat(input.getDescription()).isEqualTo(rating.getDescription());

        return rating;
    }

    private void compareInputWithRangeOfValuesDocumentAfterCreate(RatingDTO input, Rating foundRatingInDatabase) {
        RangeOfValues rangeOfValues = rangeOfValuesRepository.
                findById(foundRatingInDatabase.getRangeOfValuesId()).get();

        assertThat(input.getRangeOfValues().equalsWithoutId(rangeOfValues)).isTrue();
        compareIfRangeOfValuesExistsExactOnce(input);
    }

    private void compareIfRangeOfValuesExistsExactOnce(RatingDTO input) {
        RangeOfValues inputRangeOfValues = input.getRangeOfValues();
        ArrayList<RangeOfValues> listOfRangeOfValues =  rangeOfValuesRepository.findAll();

        int listSize = listOfRangeOfValues.size();
        int numberOfFoundRangeOfValues = 0;
        for (int index = 0; index < listSize; index++) {
            if (listOfRangeOfValues.get(index).equalsWithoutId(inputRangeOfValues)) {
                numberOfFoundRangeOfValues++;
            }
        }
        assertThat(numberOfFoundRangeOfValues).isEqualTo(MAX_RANGE_OF_VALUES_WITH_SAME_ATTRIBUTES_IN_DOCUMENT);
    }

    private void compareInputRatingEntryDocumentAfterCreate(RatingDTO input) {
        ArrayList<RatingEntry> ratingEntries = ratingEntryRepository.findAllByRatingId(input.getId());
        assertThat(ratingEntries.size()).isEqualTo(NUMBER_OF_RATING_ENTRIES_AFTER_CREATE_RATING);
    }

    private void login(User user) {
        try {
            authenticationController.login(new LoginDTO(user.getEmail(), user.getPassword()),
                    createHttpServletResponse());
        } catch (Exception e) {

        }
    }

    private void logout() {
        clearContext();
    }

    private User storeUserInDatabase(User user) {
        String rawPassword = user.getPassword();
        try {
            userController.create(user);
        } catch (Exception ignored) {

        }
        user.setPassword(rawPassword);
        return user;
    }

}
