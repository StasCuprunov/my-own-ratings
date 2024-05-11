package en.ratings.own.my.test.integration.controller.utility.rating;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RangeOfValues;

import static en.ratings.own.my.test.integration.controller.utility.rating.CreateRangeOfValuesUtility.VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM;

public class CreateRatingDTOUtility {

    public static RatingDTO createRatingDTOWithNoUserId(String name, String description, RangeOfValues rangeOfValues) {
        return new RatingDTO("", name, description, rangeOfValues);
    }

    public static RatingDTO createRatingDTOWithNoUserIdAndNoDescription(String name, RangeOfValues rangeOfValues) {
        return new RatingDTO("", name, rangeOfValues);
    }

    public static final RatingDTO INVALID_RATING_DTO_BECAUSE_EMPTY_NAME =
            createRatingDTOWithNoUserId("         ", "Not allowed.",
                    VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM);
}
