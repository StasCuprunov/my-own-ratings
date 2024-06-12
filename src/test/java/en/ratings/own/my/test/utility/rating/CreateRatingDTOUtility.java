package en.ratings.own.my.test.utility.rating;

import en.ratings.own.my.dto.rating.RatingDTO;
import en.ratings.own.my.model.rating.RangeOfValues;

import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_DESCRIPTION;
import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_NAME;
import static en.ratings.own.my.test.utility.GeneratorUtility.generateRandomAlphabeticString;
import static en.ratings.own.my.test.utility.GeneratorUtility.numberGreaterThanMaximum;
import static en.ratings.own.my.test.utility.rating.CreateRangeOfValuesUtility.
        VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM;

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
                    VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM);

    public static RatingDTO createInvalidRatingDTOWithTooLongName()  {
        int numberOfCharacters = numberGreaterThanMaximum(MAX_LENGTH_OF_NAME);

        return createRatingDTOWithNoUserId(generateRandomAlphabeticString(numberOfCharacters),"Not allowed.",
                VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM);
    }

    public static RatingDTO createInvalidRatingDTOWithTooLongDescription() {
        int numberOfCharacters = numberGreaterThanMaximum(MAX_LENGTH_OF_DESCRIPTION);

        return createRatingDTOWithNoUserId("Not allowed", generateRandomAlphabeticString(numberOfCharacters),
                VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM);
    }
}
