package en.ratings.own.my.test.utility.rating;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RatingEntry;

import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_MINIMUM_EQUALS_TO_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_MINIMUM_GREATER_THAN_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        INVALID_RANGE_OF_VALUES_WITH_UNAVAILABLE_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        VALID_RANGE_OF_VALUES_AS_AMAZON_RATING;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        createValidRangeOfValuesWithNegativeMinimum;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM;
import static en.ratings.own.my.test.utility.rating.CreateRatingDTOUtility.
        createRatingDTOWithNoUserId;
import static en.ratings.own.my.test.utility.rating.CreateRatingDTOUtility.
        createRatingDTOWithNoUserIdAndNoDescription;

public class RatingDrinksUtility {

    public static final String DRINKS_NAME = "Drink list:";

    public static final String DRINKS_DESCRIPTION = "My personal rating:";

    public static final String DRINKS_IN_ASIA_NAME = "My favourite drinks in Asia";

    public static final String DRINKS_IN_ASIA_DESCRIPTION = "Asia drinks are awesome!";

    public static final String DRINK_APPLE_JUICE = "apple juice";
    
    public static RatingDTO createValidRatingDTODrinksWithNegativeMinimum() {
        return createRatingDTOWithNoUserId(DRINKS_NAME, DRINKS_DESCRIPTION,
                createValidRangeOfValuesWithNegativeMinimum());
    }

    public static RatingDTO createValidRatingDTODrinkInAsiaWithAmazonRating() {
        return createRatingDTOWithNoUserId(DRINKS_IN_ASIA_NAME, DRINKS_IN_ASIA_DESCRIPTION,
                VALID_RANGE_OF_VALUES_AS_AMAZON_RATING);
    }

    public static RatingDTO VALID_RATING_DTO_DRINKS_WITH_NO_DESCRIPTION_AND_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM =
            createRatingDTOWithNoUserIdAndNoDescription(DRINKS_NAME,
                    VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM);

    public static RatingDTO INVALID_RATING_DTO_WITH_EMPTY_NAME_AND_NO_DESCRIPTION_AND_WITH_GERMAN_GRADING =
            createRatingDTOWithNoUserIdAndNoDescription("", VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING);

    public static RatingDTO INVALID_RATING_DTO_DRINKS_WITH_MINIMUM_EQUALS_TO_MAXIMUM =
            createRatingDTOWithNoUserId(DRINKS_NAME, DRINKS_DESCRIPTION,
                    INVALID_RANGE_OF_VALUES_WITH_MINIMUM_EQUALS_TO_MAXIMUM);

    public static RatingDTO INVALID_RATING_DTO_DRINKS_WITH_MINIMUM_GREATER_THAN_MAXIMUM =
            createRatingDTOWithNoUserId(DRINKS_NAME, DRINKS_DESCRIPTION,
                    INVALID_RANGE_OF_VALUES_WITH_MINIMUM_GREATER_THAN_MAXIMUM);

    public static RatingDTO INVALID_RATING_DTO_DRINKS_WITH_UNAVAILABLE_MAXIMUM =
            createRatingDTOWithNoUserId(DRINKS_NAME, DRINKS_DESCRIPTION,
                    INVALID_RANGE_OF_VALUES_WITH_UNAVAILABLE_MAXIMUM);

    public static RatingEntry createValidRatingEntryCokeForDrinksWithNegativeMinimum(String ratingId) {
        return new RatingEntry(ratingId, "Coke", -2.2);
    }

    public static RatingEntry createValidRatingEntryAppleJuiceForDrinksWithNegativeMinimum(String ratingId) {
        return new RatingEntry(ratingId, DRINK_APPLE_JUICE, -3.4);
    }

    public static RatingEntry createValidRatingEntryRedBullForDrinksWithNegativeMinimum(String ratingId) {
        return new RatingEntry(ratingId, "I love Red Bull!", 0.0);
    }
}
