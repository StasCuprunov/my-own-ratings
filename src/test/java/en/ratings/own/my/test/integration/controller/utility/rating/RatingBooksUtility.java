package en.ratings.own.my.test.integration.controller.utility.rating;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RatingEntry;

import java.util.ArrayList;

import static en.ratings.own.my.test.integration.controller.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_NEGATIVE_STEP_WIDTH;
import static en.ratings.own.my.test.integration.controller.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_ZERO_STEP_WIDTH;
import static en.ratings.own.my.test.integration.controller.utility.rating.CreateRangeOfValuesUtility.
        VALID_RANGE_OF_VALUES_AS_AMAZON_RATING;
import static en.ratings.own.my.test.integration.controller.utility.rating.CreateRangeOfValuesUtility.
        VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING;
import static en.ratings.own.my.test.integration.controller.utility.rating.CreateRatingDTOUtility.
        createRatingDTOWithNoUserId;
import static java.lang.Math.PI;

public class RatingBooksUtility {

    private static final String BOOKS_NAME = "Books";

    private static final String BOOKS_DESCRIPTION = "This are my favourite books!";

    public static RatingDTO VALID_RATING_DTO_BOOKS_WITH_GERMAN_GRADING =
            createRatingDTOWithNoUserId(BOOKS_NAME, BOOKS_DESCRIPTION, VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING);

    public static RatingDTO VALID_RATING_DTO_BOOKS_WITH_AMAZON_RATING =
            createRatingDTOWithNoUserId(BOOKS_NAME, BOOKS_DESCRIPTION, VALID_RANGE_OF_VALUES_AS_AMAZON_RATING);

    public static RatingDTO INVALID_RATING_DTO_BOOKS_WITH_NEGATIVE_STEP_WIDTH =
            createRatingDTOWithNoUserId(BOOKS_NAME, BOOKS_DESCRIPTION,
                    INVALID_RANGE_OF_VALUES_WITH_NEGATIVE_STEP_WIDTH);

    public static RatingDTO INVALID_RATING_DTO_BOOKS_WITH_ZERO_STEP_WIDTH =
            createRatingDTOWithNoUserId(BOOKS_NAME, BOOKS_DESCRIPTION,
                    INVALID_RANGE_OF_VALUES_WITH_ZERO_STEP_WIDTH);

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
