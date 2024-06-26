package en.ratings.own.my.test.integration.controller.rating;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.User;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.Rating;
import en.ratings.own.my.model.rating.RatingEntry;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static en.ratings.own.my.test.constant.TestConstants.EXPECTED_ZERO;
import static en.ratings.own.my.test.utility.GeneratorUtility.ID_TEST;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAccessDeniedException;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException;
import static en.ratings.own.my.test.utility.asserts.AssertThatExceptionUtility.
        assertThatExceptionIsEqualToRatingCreationFailedException;
import static en.ratings.own.my.test.utility.asserts.AssertThatUtility.assertThatIdIsDefined;
import static en.ratings.own.my.test.utility.asserts.AssertThatStatusCodeUtility.assertThatStatusCodeIsCreated;
import static en.ratings.own.my.test.utility.rating.CreateRatingDTOUtility.
        INVALID_RATING_DTO_BECAUSE_EMPTY_NAME;
import static en.ratings.own.my.test.utility.rating.CreateRatingDTOUtility.createInvalidRatingDTOWithTooLongDescription;
import static en.ratings.own.my.test.utility.rating.CreateRatingDTOUtility.createInvalidRatingDTOWithTooLongName;
import static en.ratings.own.my.test.utility.rating.CreateRatingDTOUtility.createNewRatingDTOObject;
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
        testValidCreate(userStevenWorm, createNewRatingDTOObject(VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING));
    }

    @Test
    public void testValidCreateWithDefinedRatingEntries() {
        testValidCreate(userStevenWorm,
                createNewRatingDTOObject(createValidRatingDTOBooksWithGermanGradingAndDefinedRatingEntries()));
    }

    @Test
    public void testValidCreateWithSameInputsButDifferentUsers() {
        RatingDTO input = VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
        testValidCreate(userStevenWorm, createNewRatingDTOObject(input));
        testValidCreate(userFalakNoorahKhoury, createNewRatingDTOObject(input));
    }

    @Test
    public void testValidCreateWithSameRatingNamesButDifferentUsers() {
        testValidCreate(userStevenWorm, createNewRatingDTOObject(VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING));
        testValidCreate(userFalakNoorahKhoury, createNewRatingDTOObject(VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING));
    }

    @Test
    public void testValidCreateWithNegativeMinimum() {
        testValidCreate(userFalakNoorahKhoury,
                createNewRatingDTOObject(createValidRatingDTODrinksWithNegativeMinimum()));
    }

    @Test
    public void testValidCreateWithNoDescriptionAndWithNegativeMinimumAndMaximum() {
        testValidCreate(userStevenWorm, createNewRatingDTOObject(
                VALID_RATING_DTO_DRINKS_WITH_NO_DESCRIPTION_AND_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM));
    }

    @Test
    public void testInvalidCreateWithoutLoggedIn() {
        logout();
        RatingDTO input = createNewRatingDTOObject(VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING);
        input.setUserId(userStevenWorm.getId());
        Exception foundException = createInvalid(input);
        assertThatExceptionIsEqualToAuthenticationCredentialsNotFoundException(foundException);
    }

    @Test
    public void testInvalidCreateWithDefinedId() {
        RatingDTO input = createNewRatingDTOObject(VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING);
        input.setId(ID_TEST);
        testInvalidCreateExpectedRatingCreationFailedException(userStevenWorm, input);
    }

    @Test
    public void testInvalidCreateWithEmptyName() {
        testInvalidCreateExpectedRatingCreationFailedException(userStevenWorm,
                createNewRatingDTOObject(INVALID_RATING_DTO_BECAUSE_EMPTY_NAME));
    }

    @Test
    public void testInvalidCreateWithTooLongName() {
        testInvalidCreateExpectedRatingCreationFailedException(userStevenWorm, createInvalidRatingDTOWithTooLongName());
    }

    @Test
    public void testInvalidCreateWithTooLongDescription() {
        testInvalidCreateExpectedRatingCreationFailedException(userStevenWorm,
                createInvalidRatingDTOWithTooLongDescription());
    }

    @Test
    public void testInvalidCreateWithTooSmallMinimum() {
        testInvalidCreateExpectedRatingCreationFailedException(userStevenWorm,
                createNewRatingDTOObject(INVALID_RATING_DTO_BOOKS_WITH_TOO_SMALL_MINIMUM));
    }

    @Test
    public void testInvalidCreateWithTooBigMaximum() {
        testInvalidCreateExpectedRatingCreationFailedException(userStevenWorm,
                createNewRatingDTOObject(INVALID_RATING_DTO_BOOKS_WITH_TOO_BIG_MAXIMUM));
    }

    @Test
    public void testInvalidCreateWithMinimumTooManyDecimalDigits() {
        testInvalidCreateExpectedRatingCreationFailedException(userStevenWorm,
                createNewRatingDTOObject(INVALID_RATING_DTO_BOOKS_WITH_MINIMUM_TOO_MANY_DECIMAL_DIGITS));
    }

    @Test
    public void testInvalidCreateWithMaximumAndStepWidthWithTooManyDecimalDigits() {
        testInvalidCreateExpectedRatingCreationFailedException(userStevenWorm, createNewRatingDTOObject(
                INVALID_RATING_DTO_BOOKS_WITH_MAXIMUM_AND_STEP_WIDTH_WITH_TOO_MANY_DECIMAL_DIGITS));
    }

    @Test
    public void testInvalidCreateWithStepWidthTooManyDecimalDigits() {
        testInvalidCreateExpectedRatingCreationFailedException(userStevenWorm,
                createNewRatingDTOObject(INVALID_RATING_DTO_BOOKS_WITH_STEP_WIDTH_TOO_MANY_DECIMAL_DIGITS));
    }

    @Test
    public void testInvalidCreateWithNegativeStepWidth() {
        testInvalidCreateExpectedRatingCreationFailedException(userFalakNoorahKhoury,
                createNewRatingDTOObject(INVALID_RATING_DTO_BOOKS_WITH_NEGATIVE_STEP_WIDTH));
    }

    @Test
    public void testInvalidCreateWithZeroStepWidth() {
        testInvalidCreateExpectedRatingCreationFailedException(userFalakNoorahKhoury,
                createNewRatingDTOObject(INVALID_RATING_DTO_BOOKS_WITH_ZERO_STEP_WIDTH));
    }

    @Test
    public void testInvalidCreateWithEmptyNameAndNoDescription() {
        testInvalidCreateExpectedRatingCreationFailedException(userStevenWorm, createNewRatingDTOObject(
                INVALID_RATING_DTO_WITH_EMPTY_NAME_AND_NO_DESCRIPTION_AND_WITH_GERMAN_GRADING));
    }

    @Test
    public void testInvalidCreateWithMinimumEqualsToMaximum() {
        testInvalidCreateExpectedRatingCreationFailedException(userStevenWorm,
                createNewRatingDTOObject(INVALID_RATING_DTO_DRINKS_WITH_MINIMUM_EQUALS_TO_MAXIMUM));
    }

    @Test
    public void testInvalidCreateWithMinimumGreaterThanMaximum() {
        testInvalidCreateExpectedRatingCreationFailedException(userFalakNoorahKhoury,
                createNewRatingDTOObject(INVALID_RATING_DTO_DRINKS_WITH_MINIMUM_GREATER_THAN_MAXIMUM));
    }

    @Test
    public void testInvalidCreateWithUnavailableMaximum() {
        testInvalidCreateExpectedRatingCreationFailedException(userStevenWorm,
                createNewRatingDTOObject(INVALID_RATING_DTO_DRINKS_WITH_UNAVAILABLE_MAXIMUM));
    }

    @Test
    public void testInvalidCreateWithForeignUserId() {
        testInvalidCreateWithExpectedAccessDeniedException(userStevenWorm,
                createNewRatingDTOObject(VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING),
                userFalakNoorahKhoury.getId());
    }

    private void testValidCreate(User user, RatingDTO input) {
        testValidCreate(user, input, null);
    }

    private void testValidCreate(User user, RatingDTO input, String differentUserId) {
        ResponseEntity<RatingDTO> responseEntity = createValidRating(user, input, differentUserId);
        checkValidCreate(input, responseEntity);
    }

    private Exception testInvalidCreate(User user, RatingDTO input, String differentUserId) {
        login(user);
        String id = (differentUserId == null) ? user.getId() : differentUserId;
        input.setUserId(id);
        return createInvalid(input);
    }

    private void testInvalidCreateExpectedRatingCreationFailedException(User user, RatingDTO input) {
        Exception foundException = testInvalidCreate(user, input, null);
        assertThatExceptionIsEqualToRatingCreationFailedException(foundException);
    }

    private void testInvalidCreateWithExpectedAccessDeniedException(User user, RatingDTO input,
                                                                    String differentUserId) {
        Exception foundException = testInvalidCreate(user, input, differentUserId);
        assertThatExceptionIsEqualToAccessDeniedException(foundException);
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
        assertThat(ratingEntries.size()).isEqualTo(EXPECTED_ZERO);
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
