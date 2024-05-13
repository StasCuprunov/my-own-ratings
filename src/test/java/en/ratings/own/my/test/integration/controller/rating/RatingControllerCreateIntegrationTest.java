package en.ratings.own.my.test.integration.controller.rating;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.User;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingCreationFailedException;
import static en.ratings.own.my.test.utility.asserts.AssertThatUtility.assertThatIdIsDefined;
import static en.ratings.own.my.test.utility.asserts.AssertThatStatusCodeUtility.assertThatStatusCodeIsCreated;
import static en.ratings.own.my.test.utility.rating.CreateRatingDTOUtility.
        INVALID_RATING_DTO_BECAUSE_EMPTY_NAME;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        INVALID_RATING_DTO_BOOKS_WITH_MAXIMUM_AND_STEP_WIDTH_WITH_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        INVALID_RATING_DTO_BOOKS_WITH_MINIMUM_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        INVALID_RATING_DTO_BOOKS_WITH_NEGATIVE_STEP_WIDTH;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        INVALID_RATING_DTO_BOOKS_WITH_STEP_WIDTH_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        INVALID_RATING_DTO_BOOKS_WITH_TOO_BIG_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        INVALID_RATING_DTO_BOOKS_WITH_TOO_SMALL_MINIMUM;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        INVALID_RATING_DTO_BOOKS_WITH_ZERO_STEP_WIDTH;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        createValidRatingDTOBooksWithGermanGradingAndDefinedRatingEntries;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        INVALID_RATING_DTO_DRINKS_WITH_MINIMUM_EQUALS_TO_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        INVALID_RATING_DTO_DRINKS_WITH_MINIMUM_GREATER_THAN_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        INVALID_RATING_DTO_DRINKS_WITH_UNAVAILABLE_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        INVALID_RATING_DTO_WITH_EMPTY_NAME_AND_NO_DESCRIPTION_AND_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingDTODrinksWithNegativeMinimum;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        VALID_RATING_DTO_DRINKS_WITH_NO_DESCRIPTION_AND_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RatingControllerCreateIntegrationTest extends RatingControllerIntegrationTest {
    @Test
    public void testValidCreate() {
        testValidCreate(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING);
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
        testValidCreate(userFalakNoorahKhoury, createValidRatingDTODrinksWithNegativeMinimum());
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
        Exception foundException = createInvalid(input);
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(foundException);
    }

    @Test
    public void testInvalidCreateWithDefinedId() {
        RatingDTO input = VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
        input.setId("test");
        testInvalidCreate(userStevenWorm, input);
    }

    @Test
    public void testInvalidCreateWithEmptyName() {
        testInvalidCreate(userStevenWorm, INVALID_RATING_DTO_BECAUSE_EMPTY_NAME);
    }

    @Test
    public void testInvalidCreateWithTooSmallMinimum() {
        testInvalidCreate(userStevenWorm, INVALID_RATING_DTO_BOOKS_WITH_TOO_SMALL_MINIMUM);
    }

    @Test
    public void testInvalidCreateWithTooBigMaximum() {
        testInvalidCreate(userStevenWorm, INVALID_RATING_DTO_BOOKS_WITH_TOO_BIG_MAXIMUM);
    }

    @Test
    public void testInvalidCreateWithMinimumTooManyDecimalDigits() {
        testInvalidCreate(userStevenWorm, INVALID_RATING_DTO_BOOKS_WITH_MINIMUM_TOO_MANY_DECIMAL_DIGITS);
    }

    @Test
    public void testInvalidCreateWithMaximumAndStepWidthWithTooManyDecimalDigits() {
        testInvalidCreate(userStevenWorm,
                INVALID_RATING_DTO_BOOKS_WITH_MAXIMUM_AND_STEP_WIDTH_WITH_TOO_MANY_DECIMAL_DIGITS);
    }

    @Test
    public void testInvalidCreateWithStepWidthTooManyDecimalDigits() {
        testInvalidCreate(userStevenWorm,
                INVALID_RATING_DTO_BOOKS_WITH_STEP_WIDTH_TOO_MANY_DECIMAL_DIGITS);
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
        ResponseEntity<RatingDTO> responseEntity = createValidRating(user, input, differentUserId);
        checkValidCreate(input, responseEntity);
    }

    private void testInvalidCreate(User user, RatingDTO input) {
        testInvalidCreate(user, input, null);
    }

    private void testInvalidCreate(User user, RatingDTO input, String differentUserId) {
        login(user);
        String id = (differentUserId == null) ? user.getId() : differentUserId;
        input.setUserId(id);
        Exception foundException = createInvalid(input);
        assertThatExceptionIsEqualToRatingCreationFailedException(foundException);
    }

    private void checkValidCreate(RatingDTO input, ResponseEntity<RatingDTO> responseEntity) {
        RatingDTO result = responseEntity.getBody();
        assertAll("Test valid create rating",
                () -> assertThatStatusCodeIsCreated(responseEntity),
                () -> compareInputWithResultAfterValidCreate(input, result),
                () -> compareResultWithDatabaseAfterValidCreate(result)
        );
    }

    private void compareInputWithResultAfterValidCreate(RatingDTO input, RatingDTO result) {
        assertThatIdIsDefined(result.getUserId());
        assertThat(input.equalsAfterCreate(result)).isTrue();
    }

    private void compareResultWithDatabaseAfterValidCreate(RatingDTO input) {
        Rating rating = compareInputWithRatingDocumentAfterCreate(input);
        compareInputWithRangeOfValuesDocumentAfterCreate(input, rating);
        compareInputRatingEntryDocumentAfterCreate(input);
    }

    private Rating compareInputWithRatingDocumentAfterCreate(RatingDTO input) {
        Rating rating = findByIdRatingRepository(input.getId()).get();

        assertThat(input.getUserId()).isEqualTo(rating.getUserId());
        assertThat(input.getName()).isEqualTo(rating.getName());
        assertThat(input.getDescription()).isEqualTo(rating.getDescription());

        return rating;
    }

    private void compareInputWithRangeOfValuesDocumentAfterCreate(RatingDTO input, Rating foundRatingInDatabase) {
        RangeOfValues rangeOfValues = findByIdRangeOfValuesRepository(foundRatingInDatabase.getRangeOfValuesId()).get();

        assertThat(input.getRangeOfValues().equalsWithoutId(rangeOfValues)).isTrue();
        compareIfRangeOfValuesExistsExactOnce(input);
    }

    private void compareInputRatingEntryDocumentAfterCreate(RatingDTO input) {
        ArrayList<RatingEntry> ratingEntries = findAllByRatingIdRatingEntryRepository(input.getId());
        assertThat(ratingEntries.size()).isEqualTo(NUMBER_OF_RATING_ENTRIES_AFTER_CREATE_RATING);
    }

    private Exception createInvalid(RatingDTO input) {
        try {
            ratingController.create(input);
        } catch (Exception e) {
            return e;
        }
        return new Exception();
    }
}
