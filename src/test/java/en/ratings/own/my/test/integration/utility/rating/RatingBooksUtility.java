package en.ratings.own.my.test.integration.utility.rating;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RatingEntry;

import java.util.ArrayList;

import static en.ratings.own.my.test.integration.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_NEGATIVE_STEP_WIDTH;
import static en.ratings.own.my.test.integration.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_TOO_BIG_MAXIMUM;
import static en.ratings.own.my.test.integration.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_TOO_SMALL_MINIMUM;
import static en.ratings.own.my.test.integration.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_ZERO_STEP_WIDTH;
import static en.ratings.own.my.test.integration.utility.rating.CreateRangeOfValuesUtility.
        VALID_RANGE_OF_VALUES_AS_AMAZON_RATING;
import static en.ratings.own.my.test.integration.utility.rating.CreateRangeOfValuesUtility.
        VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING;
import static en.ratings.own.my.test.integration.utility.rating.CreateRangeOfValuesUtility.
        createValidRangeOfValuesWithNegativeMinimum;
import static en.ratings.own.my.test.integration.utility.rating.CreateRangeOfValuesUtility.
        createInvalidRangeOfValuesWithMaximumAndStepWidthWithTooManyDecimalDigits;
import static en.ratings.own.my.test.integration.utility.rating.CreateRangeOfValuesUtility.
        createInvalidRangeOfValuesWithMinimumTooManyDecimalDigits;
import static en.ratings.own.my.test.integration.utility.rating.CreateRangeOfValuesUtility.
        createInvalidRangeOfValuesWithStepWidthTooManyDecimalDigits;
import static en.ratings.own.my.test.integration.utility.rating.CreateRatingDTOUtility.
        createRatingDTOWithNoUserId;
import static java.lang.Math.PI;

public class RatingBooksUtility {

    public static final String BOOKS_NAME = "Books";

    public static final String BOOKS_DESCRIPTION = "This are my favourite books!";

    public static final String BOOKS_SCIENTIFIC_NAME = "These are the most useful scientific books!";

    public static final String BOOKS_SCIENTIFIC_DESCRIPTION = "These books are rated with the German grading.";

    public static RatingDTO VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING =
            createRatingDTOWithNoUserId(BOOKS_NAME, BOOKS_DESCRIPTION, VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING);

    public static RatingDTO VALID_RATING_DTO_BOOKS_SCIENTIFIC_WITH_GERMAN_GRADING =
            createRatingDTOWithNoUserId(BOOKS_SCIENTIFIC_NAME, BOOKS_SCIENTIFIC_DESCRIPTION,
                    VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING);

    public static RatingDTO VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING =
            createRatingDTOWithNoUserId(BOOKS_NAME, BOOKS_DESCRIPTION, VALID_RANGE_OF_VALUES_AS_AMAZON_RATING);

    public static RatingDTO VALID_RATING_DTO_BOOKS_WITH_NEGATIVE_MINIMUM =
            createRatingDTOWithNoUserId(BOOKS_NAME, BOOKS_DESCRIPTION, createValidRangeOfValuesWithNegativeMinimum());

    public static RatingDTO INVALID_RATING_DTO_BOOKS_WITH_NEGATIVE_STEP_WIDTH =
            createRatingDTOWithNoUserId(BOOKS_NAME, BOOKS_DESCRIPTION,
                    INVALID_RANGE_OF_VALUES_WITH_NEGATIVE_STEP_WIDTH);

    public static RatingDTO INVALID_RATING_DTO_BOOKS_WITH_ZERO_STEP_WIDTH =
            createRatingDTOWithNoUserId(BOOKS_NAME, BOOKS_DESCRIPTION, INVALID_RANGE_OF_VALUES_WITH_ZERO_STEP_WIDTH);

    public static RatingDTO INVALID_RATING_DTO_BOOKS_WITH_TOO_SMALL_MINIMUM =
            createRatingDTOWithNoUserId(BOOKS_NAME, BOOKS_DESCRIPTION, INVALID_RANGE_OF_VALUES_WITH_TOO_SMALL_MINIMUM);

    public static RatingDTO INVALID_RATING_DTO_BOOKS_WITH_TOO_BIG_MAXIMUM =
            createRatingDTOWithNoUserId(BOOKS_NAME, BOOKS_DESCRIPTION, INVALID_RANGE_OF_VALUES_WITH_TOO_BIG_MAXIMUM);

    public static RatingDTO INVALID_RATING_DTO_BOOKS_WITH_MINIMUM_TOO_MANY_DECIMAL_DIGITS =
            createRatingDTOWithNoUserId(BOOKS_NAME, BOOKS_DESCRIPTION,
                    createInvalidRangeOfValuesWithMinimumTooManyDecimalDigits());

    public static RatingDTO INVALID_RATING_DTO_BOOKS_WITH_MAXIMUM_AND_STEP_WIDTH_WITH_TOO_MANY_DECIMAL_DIGITS =
            createRatingDTOWithNoUserId(BOOKS_NAME, BOOKS_DESCRIPTION,
                    createInvalidRangeOfValuesWithMaximumAndStepWidthWithTooManyDecimalDigits());

    public static RatingDTO INVALID_RATING_DTO_BOOKS_WITH_STEP_WIDTH_TOO_MANY_DECIMAL_DIGITS =
            createRatingDTOWithNoUserId(BOOKS_NAME, BOOKS_DESCRIPTION,
                    createInvalidRangeOfValuesWithStepWidthTooManyDecimalDigits());

    public static RatingDTO createValidRatingDTOBooksWithGermanGradingAndDefinedRatingEntries() {
        RatingDTO ratingDTO = VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING;
        ArrayList<RatingEntry> ratingEntries = new ArrayList<>();
        ratingEntries.add(createValidRatingEntryTheGreatGatsbyForBooksWithGermanGrading("abc"));
        ratingDTO.setRatingEntries(ratingEntries);
        return ratingDTO;
    }

    public static RatingEntry createValidRatingEntryTheGreatGatsbyForBooksWithGermanGrading(String ratingId) {
        return new RatingEntry(ratingId, "The Great Gatsby", 4.0);
    }

    public static RatingEntry createValidRatingEntryMobyDickForBooksWithGermanGrading(String ratingId) {
        return new RatingEntry(ratingId, "Moby Dick by Herman Melville", 2.0);
    }

    public static RatingEntry createInvalidRatingEntryBecauseEmptyNameForBooksWithGermanGrading(String ratingId) {
        return new RatingEntry(ratingId, "                ", 2.0);
    }

    public static RatingEntry createInvalidRatingEntryBecauseInvalidValueForBooksWithAmazonRating(String ratingId) {
        return new RatingEntry(ratingId, "The Lord of the Rings", PI);
    }

}
