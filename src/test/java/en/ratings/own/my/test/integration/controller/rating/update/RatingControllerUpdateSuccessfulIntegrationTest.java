package en.ratings.own.my.test.integration.controller.rating.update;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.RatingEntry;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static en.ratings.own.my.test.integration.utility.rating.CreateRangeOfValuesUtility.
        VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.BOOKS_NAME;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.BOOKS_SCIENTIFIC_NAME;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_SCIENTIFIC_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.integration.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.DRINKS_IN_ASIA_DESCRIPTION;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.DRINKS_IN_ASIA_NAME;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM;
import static en.ratings.own.my.test.integration.utility.rating.RatingDrinksUtility.
        createValidRatingEntryCokeForDrinksWithNegativeMinimum;

public class RatingControllerUpdateSuccessfulIntegrationTest extends RatingControllerUpdateIntegrationTest {

    @Test
    public void testValidUpdateWithName() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING);
        RatingDTO input = responseCreate.getBody();
        input.setName(BOOKS_SCIENTIFIC_NAME);
        testValidUpdate(input, updateSuccessful(input));
    }

    @Test
    public void testValidUpdateWithNameWhichIsAlsoUsedByAnotherUser() {
        createValidRating(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING);
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userFalakNoorahKhoury,
                VALID_RATING_DTO_BOOKS_SCIENTIFIC_WITH_GERMAN_GRADING);
        RatingDTO input = responseCreate.getBody();
        input.setName(BOOKS_NAME);
        testValidUpdate(input, updateSuccessful(input));
    }

    @Test
    public void testValidUpdateWithNameAndDefinedRatingEntriesInInput() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM);
        RatingDTO input = responseCreate.getBody();
        input.setName(DRINKS_IN_ASIA_NAME);
        ArrayList<RatingEntry> ratingEntries = new ArrayList<>();
        ratingEntries.add(createValidRatingEntryCokeForDrinksWithNegativeMinimum(input.getId()));
        input.setRatingEntries(ratingEntries);
        testValidUpdate(input, updateSuccessful(input));
        checkIfRatingEntriesInInputAreCreatedWithUpdate(input);
    }

    @Test
    public void testValidUpdateWithDescription() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM);
        RatingDTO input = responseCreate.getBody();
        input.setDescription(DRINKS_IN_ASIA_DESCRIPTION);
        testValidUpdate(input, updateSuccessful(input));
    }

    @Test
    public void testValidUpdateWithEmptyDescription() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM);
        RatingDTO input = responseCreate.getBody();
        input.setDescription("");
        testValidUpdate(input, updateSuccessful(input));
    }

    @Test
    public void testValidUpdateWithNameAndDescription() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM);
        RatingDTO input = responseCreate.getBody();
        input.setName(DRINKS_IN_ASIA_NAME);
        input.setDescription(DRINKS_IN_ASIA_DESCRIPTION);
        testValidUpdate(input, updateSuccessful(input));
    }

    @Test
    public void testValidUpdateWithMinimumAndDeletingOldRangeOfValues() {
        RangeOfValues oldRangeOfValues = createNewRangeOfValuesObject(VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM);
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM);
        RatingDTO input = responseCreate.getBody();
        input.getRangeOfValues().setMinimum(2 * VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM.getMinimum());

        testValidUpdate(input, updateSuccessful(input));
        checkIfOldRangeOfValuesIsDeleted(oldRangeOfValues);
    }

    @Test
    public void testValidUpdateWithMinimumAndWithExistentRatingEntries() {
        RangeOfValues oldRangeOfValues = createNewRangeOfValuesObject(VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM);
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                VALID_RATING_DTO_DRINKS_WITH_NEGATIVE_MINIMUM);
        createValidRating(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM);

        RatingDTO input = responseCreate.getBody();
        createRatingEntriesForDrinksWithNegativeMinimum(input.getId());
        RangeOfValues newRangeOfValues = input.getRangeOfValues();
        newRangeOfValues.setMinimum(2 * newRangeOfValues.getMinimum());

        testValidUpdate(input, updateSuccessful(input));
        checkIfOldRangeOfValuesIsAvailable(oldRangeOfValues);
    }

    @Test
    public void testValidUpdateWithMaximumAndWithExistentRatingEntries() {

    }

    @Test
    public void testValidUpdateWithStepWidthAndWithExistentRatingEntries() {

    }

    @Test
    public void testValidUpdateWithMinimumAndMaximumAndWithExistentRatingEntries() {

    }

    @Test
    public void testValidUpdateWithMinimumAndStepWidthAndWithExistentRatingEntries() {

    }

    @Test
    public void testValidUpdateWithMaximumAndStepWidthAndWithExistentRatingEntries() {

    }

    @Test
    public void testValidUpdateWithMinimumAndMaximumAndStepWidthAndWithExistentRatingEntries() {

    }

    @Test
    public void testValidUpdateWithNameAndMaximumAndWithExistentRatingEntries() {

    }
}
