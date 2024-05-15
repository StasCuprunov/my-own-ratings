package en.ratings.own.my.test.utility.rating;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RangeOfValues;

public class CreateRatingDTOUtility {

    public static RatingDTO createRatingDTOWithNoUserId(String name, String description, RangeOfValues rangeOfValues) {
        return new RatingDTO("", name, description, rangeOfValues);
    }

    public static RatingDTO createRatingDTOWithNoUserIdAndNoDescription(String name, RangeOfValues rangeOfValues) {
        return new RatingDTO("", name, rangeOfValues);
    }

    public static RatingDTO creatRatingDTOWithUserId(String userId, RatingDTO ratingDTO) {
        ratingDTO.setUserId(userId);
        return ratingDTO;
    }

    public static RatingDTO createNewRatingDTOObject(RatingDTO ratingDTO) {
        return new RatingDTO(null, ratingDTO.getUserId(), ratingDTO.getName(), ratingDTO.getDescription(),
                ratingDTO.getRangeOfValues(), ratingDTO.getRatingEntries());
    }

    public static final RatingDTO INVALID_RATING_DTO_BECAUSE_EMPTY_NAME =
            createRatingDTOWithNoUserId("         ", "Not allowed.",
                    CreateRangeOfValuesUtility.VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM);
}
