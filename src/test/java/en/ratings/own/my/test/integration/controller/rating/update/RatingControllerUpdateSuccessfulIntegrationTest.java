package en.ratings.own.my.test.integration.controller.rating.update;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RangeOfValues;
import en.ratings.own.my.model.rating.RatingEntry;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        createValidRangeOfValuesWithNegativeMinimum;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.BOOKS_NAME;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.BOOKS_SCIENTIFIC_NAME;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_SCIENTIFIC_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
import static en.ratings.own.my.test.utility.rating.RatingBooksUtility.
        VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.DRINKS_IN_ASIA_DESCRIPTION;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.DRINKS_IN_ASIA_NAME;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingDTODrinksWithNegativeMinimum;
import static en.ratings.own.my.test.utility.rating.RatingDrinksUtility.
        createValidRatingEntryCokeForDrinksWithNegativeMinimum;
import static en.ratings.own.my.utility.StringUtility.EMPTY_STRING;

public class RatingControllerUpdateSuccessfulIntegrationTest extends RatingControllerUpdateIntegrationTest {

    private static final Double VALID_STEP_WIDTH_FOR_DRINKS_WITH_NEGATIVE_MINIMUM =
            createValidRangeOfValuesWithNegativeMinimum().getStepWidth() / 10.0;

    private static final Double VALID_MINIMUM_FOR_DRINKS_WITH_NEGATIVE_MINIMUM =
            2 * createValidRangeOfValuesWithNegativeMinimum().getMinimum();

    private static final Double VALID_MAXIMUM_FOR_DRINKS_WITH_NEGATIVE_MINIMUM =
            createValidRangeOfValuesWithNegativeMinimum().getMaximum() +
                    createValidRangeOfValuesWithNegativeMinimum().getStepWidth();

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
        RatingDTO input = createValidRatingDrinksWithNegativeMinimumAsUserStevenWorm();
        input.setName(DRINKS_IN_ASIA_NAME);
        ArrayList<RatingEntry> ratingEntries = new ArrayList<>();
        ratingEntries.add(createValidRatingEntryCokeForDrinksWithNegativeMinimum(input.getId()));
        input.setRatingEntries(ratingEntries);
        testValidUpdate(input, updateSuccessful(input));
        checkIfRatingEntriesInInputAreCreatedWithUpdate(input);
    }

    @Test
    public void testValidUpdateWithDescription() {
        RatingDTO input = createValidRatingDrinksWithNegativeMinimumAsUserStevenWorm();
        input.setDescription(DRINKS_IN_ASIA_DESCRIPTION);
        testValidUpdate(input, updateSuccessful(input));
    }

    @Test
    public void testValidUpdateWithEmptyDescription() {
        RatingDTO input = createValidRatingDrinksWithNegativeMinimumAsUserStevenWorm();
        input.setDescription(EMPTY_STRING);
        testValidUpdate(input, updateSuccessful(input));
    }

    @Test
    public void testValidUpdateWithNameAndDescription() {
        RatingDTO input = createValidRatingDrinksWithNegativeMinimumAsUserStevenWorm();
        input.setName(DRINKS_IN_ASIA_NAME);
        input.setDescription(DRINKS_IN_ASIA_DESCRIPTION);
        testValidUpdate(input, updateSuccessful(input));
    }

    @Test
    public void testValidUpdateWithMinimumAndDeletingOldRangeOfValues() {
        RangeOfValues oldRangeOfValues = createNewRangeOfValuesObject(createValidRangeOfValuesWithNegativeMinimum());
        RatingDTO input = createValidRatingDrinksWithNegativeMinimumAsUserStevenWorm();
        input.getRangeOfValues().setMinimum(VALID_MINIMUM_FOR_DRINKS_WITH_NEGATIVE_MINIMUM);

        testValidUpdate(input, updateSuccessful(input));
        checkIfOldRangeOfValuesIsDeleted(oldRangeOfValues);
    }

    @Test
    public void testValidUpdateWithMinimumAndWithExistentRatingEntries() {
        RangeOfValues oldRangeOfValues = createNewRangeOfValuesObject(createValidRangeOfValuesWithNegativeMinimum());
        RatingDTO input = createValidRatingDrinksWithNegativeMinimumAsUserStevenWorm();
        createValidRating(userFalakNoorahKhoury, VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM);

        createValidRatingEntriesForDrinksWithNegativeMinimum(input.getId());
        RangeOfValues newRangeOfValues = input.getRangeOfValues();
        newRangeOfValues.setMinimum(VALID_MINIMUM_FOR_DRINKS_WITH_NEGATIVE_MINIMUM);
        input.setRangeOfValues(newRangeOfValues);

        testValidUpdate(input, updateSuccessful(input));
        checkIfOldRangeOfValuesIsAvailable(oldRangeOfValues);
    }

    @Test
    public void testValidUpdateWithMaximumAndWithExistentRatingEntries() {
        RangeOfValues oldRangeOfValues = createNewRangeOfValuesObject(createValidRangeOfValuesWithNegativeMinimum());
        RatingDTO input = createValidRatingDrinksWithNegativeMinimumAsUserStevenWorm();
        createValidRating(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM);

        createValidRatingEntriesForDrinksWithNegativeMinimum(input.getId());
        RangeOfValues newRangeOfValues = input.getRangeOfValues();
        newRangeOfValues.setMaximum(VALID_MAXIMUM_FOR_DRINKS_WITH_NEGATIVE_MINIMUM);
        input.setRangeOfValues(newRangeOfValues);

        testValidUpdate(input, updateSuccessful(input));
        checkIfOldRangeOfValuesIsAvailable(oldRangeOfValues);
    }

    @Test
    public void testValidUpdateWithStepWidthAndWithExistentRatingEntries() {
        RangeOfValues oldRangeOfValues = createNewRangeOfValuesObject(createValidRangeOfValuesWithNegativeMinimum());
        RatingDTO input = createValidRatingDrinksWithNegativeMinimumAsUserStevenWorm();
        createValidRating(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM);

        createValidRatingEntriesForDrinksWithNegativeMinimum(input.getId());
        RangeOfValues newRangeOfValues = input.getRangeOfValues();
        newRangeOfValues.setStepWidth(VALID_STEP_WIDTH_FOR_DRINKS_WITH_NEGATIVE_MINIMUM);
        input.setRangeOfValues(newRangeOfValues);

        testValidUpdate(input, updateSuccessful(input));
        checkIfOldRangeOfValuesIsAvailable(oldRangeOfValues);
    }

    @Test
    public void testValidUpdateWithMinimumAndMaximumAndWithExistentRatingEntries() {
        RangeOfValues oldRangeOfValues = createNewRangeOfValuesObject(createValidRangeOfValuesWithNegativeMinimum());
        RatingDTO input = createValidRatingDrinksWithNegativeMinimumAsUserStevenWorm();
        createValidRating(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM);

        createValidRatingEntriesForDrinksWithNegativeMinimum(input.getId());
        RangeOfValues newRangeOfValues = input.getRangeOfValues();
        newRangeOfValues.setMinimum(VALID_MINIMUM_FOR_DRINKS_WITH_NEGATIVE_MINIMUM);
        newRangeOfValues.setMaximum(VALID_MAXIMUM_FOR_DRINKS_WITH_NEGATIVE_MINIMUM);
        input.setRangeOfValues(newRangeOfValues);

        testValidUpdate(input, updateSuccessful(input));
        checkIfOldRangeOfValuesIsAvailable(oldRangeOfValues);
    }

    @Test
    public void testValidUpdateWithMinimumAndStepWidthAndWithExistentRatingEntries() {
        RangeOfValues oldRangeOfValues = createNewRangeOfValuesObject(createValidRangeOfValuesWithNegativeMinimum());
        RatingDTO input = createValidRatingDrinksWithNegativeMinimumAsUserStevenWorm();
        createValidRating(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM);

        createValidRatingEntriesForDrinksWithNegativeMinimum(input.getId());
        RangeOfValues newRangeOfValues = input.getRangeOfValues();
        newRangeOfValues.setMinimum(VALID_MINIMUM_FOR_DRINKS_WITH_NEGATIVE_MINIMUM);
        newRangeOfValues.setStepWidth(VALID_STEP_WIDTH_FOR_DRINKS_WITH_NEGATIVE_MINIMUM);
        input.setRangeOfValues(newRangeOfValues);

        testValidUpdate(input, updateSuccessful(input));
        checkIfOldRangeOfValuesIsAvailable(oldRangeOfValues);
    }

    @Test
    public void testValidUpdateWithMaximumAndStepWidthAndWithExistentRatingEntries() {
        RangeOfValues oldRangeOfValues = createNewRangeOfValuesObject(createValidRangeOfValuesWithNegativeMinimum());
        RatingDTO input = createValidRatingDrinksWithNegativeMinimumAsUserStevenWorm();
        createValidRating(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM);

        createValidRatingEntriesForDrinksWithNegativeMinimum(input.getId());
        RangeOfValues newRangeOfValues = input.getRangeOfValues();
        newRangeOfValues.setStepWidth(VALID_STEP_WIDTH_FOR_DRINKS_WITH_NEGATIVE_MINIMUM);
        newRangeOfValues.setMaximum(VALID_MAXIMUM_FOR_DRINKS_WITH_NEGATIVE_MINIMUM);
        input.setRangeOfValues(newRangeOfValues);

        testValidUpdate(input, updateSuccessful(input));
        checkIfOldRangeOfValuesIsAvailable(oldRangeOfValues);
    }

    @Test
    public void testValidUpdateWithMinimumAndMaximumAndStepWidthAndWithExistentRatingEntries() {
        RangeOfValues oldRangeOfValues = createNewRangeOfValuesObject(createValidRangeOfValuesWithNegativeMinimum());
        RatingDTO input = createValidRatingDrinksWithNegativeMinimumAsUserStevenWorm();
        createValidRating(userStevenWorm, VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM);

        createValidRatingEntriesForDrinksWithNegativeMinimum(input.getId());
        RangeOfValues newRangeOfValues = input.getRangeOfValues();
        newRangeOfValues.setMinimum(VALID_MINIMUM_FOR_DRINKS_WITH_NEGATIVE_MINIMUM);
        newRangeOfValues.setStepWidth(VALID_STEP_WIDTH_FOR_DRINKS_WITH_NEGATIVE_MINIMUM);
        newRangeOfValues.setMaximum(VALID_MAXIMUM_FOR_DRINKS_WITH_NEGATIVE_MINIMUM);
        input.setRangeOfValues(newRangeOfValues);

        testValidUpdate(input, updateSuccessful(input));
        checkIfOldRangeOfValuesIsAvailable(oldRangeOfValues);
    }

    @Test
    public void testValidUpdateWithNameAndMaximumAndWithExistentRatingEntries() {
        RangeOfValues oldRangeOfValues = createNewRangeOfValuesObject(createValidRangeOfValuesWithNegativeMinimum());
        RatingDTO input = createValidRatingDrinksWithNegativeMinimumAsUserStevenWorm();
        createValidRating(userFalakNoorahKhoury, VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM);

        input.setName(DRINKS_IN_ASIA_NAME);
        createValidRatingEntriesForDrinksWithNegativeMinimum(input.getId());
        RangeOfValues newRangeOfValues = input.getRangeOfValues();
        newRangeOfValues.setMaximum(VALID_MAXIMUM_FOR_DRINKS_WITH_NEGATIVE_MINIMUM);
        input.setRangeOfValues(newRangeOfValues);

        testValidUpdate(input, updateSuccessful(input));
        checkIfOldRangeOfValuesIsAvailable(oldRangeOfValues);
    }

    private RatingDTO createValidRatingDrinksWithNegativeMinimumAsUserStevenWorm() {
        ResponseEntity<RatingDTO> responseCreate = createValidRating(userStevenWorm,
                createNewRatingDTOObject(createValidRatingDTODrinksWithNegativeMinimum()));
        return responseCreate.getBody();
    }
}
