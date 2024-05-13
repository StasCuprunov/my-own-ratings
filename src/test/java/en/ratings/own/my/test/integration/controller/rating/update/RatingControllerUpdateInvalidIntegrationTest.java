package en.ratings.own.my.test.integration.controller.rating.update;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RangeOfValues;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_MINIMUM_EQUALS_TO_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_MINIMUM_GREATER_THAN_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_NEGATIVE_STEP_WIDTH;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_TOO_BIG_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_TOO_SMALL_MINIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_UNAVAILABLE_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_ZERO_STEP_WIDTH;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        createInvalidRangeOfValuesWithMaximumAndStepWidthWithTooManyDecimalDigits;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        createInvalidRangeOfValuesWithMinimumTooManyDecimalDigits;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        createInvalidRangeOfValuesWithStepWidthTooManyDecimalDigits;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_SCIENTIFIC_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.DRINKS_IN_ASIA_NAME;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingDTODrinksWithNegativeMinimum;

public class RatingControllerUpdateInvalidIntegrationTest extends RatingControllerUpdateIntegrationTest {

    @Test
    public void testInvalidUpdateWithoutLoggedIn() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setName(DRINKS_IN_ASIA_NAME);
        logout();
        testUpdateInvalidWithExceptedAuthenticationCredentialsNotFoundException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithNotExistentId() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setId(createNotExistentId(input.getId()));
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    // UserId has to be the same as the authenticated user
    @Test
    public void testInvalidUpdateWithOtherUserId() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setUserId(userFalakNoorahKhoury.getId());
        testUpdateInvalidWithExceptedRatingUpdateNotAllowedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithNotExistentUserId() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setUserId(createNotExistentId(userStevenWorm.getId()));
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithEmptyName() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setName("        \n");
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithAlreadyUsedName() {
        ResponseEntity<RatingDTO> responseCreateDrinks = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        ResponseEntity<RatingDTO> responseCreateBooks = createValidRating(userStevenWorm,
                VALID_RATING_DTO_BOOKS_SCIENTIFIC_WITH_GERMAN_GRADING);
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreateBooks.getBody());
        RatingDTO input = responseCreateBooks.getBody();
        input.setName(responseCreateDrinks.getBody().getName());
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithTooSmallMinimum() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setRangeOfValues(INVALID_RANGE_OF_VALUES_WITH_TOO_SMALL_MINIMUM);
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithTooBigMaximum() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setRangeOfValues(INVALID_RANGE_OF_VALUES_WITH_TOO_BIG_MAXIMUM);
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithMinimumTooManyDecimalDigits() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setRangeOfValues(createInvalidRangeOfValuesWithMinimumTooManyDecimalDigits());
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithMaximumAndStepWidthWithTooManyDecimalDigits() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setRangeOfValues(createInvalidRangeOfValuesWithMaximumAndStepWidthWithTooManyDecimalDigits());
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithStepWidthTooManyDecimalDigits() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setRangeOfValues(createInvalidRangeOfValuesWithStepWidthTooManyDecimalDigits());
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithZeroStepWidth() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setRangeOfValues(INVALID_RANGE_OF_VALUES_WITH_ZERO_STEP_WIDTH);
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithNegativeStepWidth() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setRangeOfValues(INVALID_RANGE_OF_VALUES_WITH_NEGATIVE_STEP_WIDTH);
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithMinimumEqualsToMaximum() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setRangeOfValues(INVALID_RANGE_OF_VALUES_WITH_MINIMUM_EQUALS_TO_MAXIMUM);
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithMinimumGreaterThanMaximum() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setRangeOfValues(INVALID_RANGE_OF_VALUES_WITH_MINIMUM_GREATER_THAN_MAXIMUM);
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithUnavailableMaximum() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        input.setRangeOfValues(INVALID_RANGE_OF_VALUES_WITH_UNAVAILABLE_MAXIMUM);
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }

    @Test
    public void testInvalidUpdateWithInconsistentRatingEntryValue() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createValidRatingDTODrinksWithNegativeMinimum());
        RatingDTO createdRatingDTO = createNewRatingDTOObject(responseCreate.getBody());
        RatingDTO input = responseCreate.getBody();
        createRatingEntriesForDrinksWithNegativeMinimum(input.getId());
        RangeOfValues newRangeOfValues = createNewRangeOfValuesObject(createdRatingDTO.getRangeOfValues());
        newRangeOfValues.setStepWidth(0.5);
        input.setRangeOfValues(newRangeOfValues);
        testUpdateInvalidWithExpectedRatingUpdateFailedException(input);
        compareIfDatabaseEntriesHasNotBeenChanged(createdRatingDTO);
    }
}
