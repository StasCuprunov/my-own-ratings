package en.ratings.own.my.dto.rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static en.ratings.own.my.constant.AttributeConstants.MAXIMUM_LENGTH_OF_BIG_STRING;
import static en.ratings.own.my.constant.AttributeConstants.MAXIMUM_LENGTH_OF_SMALL_STRING;
import static en.ratings.own.my.constant.AttributeConstants.MAXIMUM_NUMBER_OF_DECIMAL_DIGITS;
import static en.ratings.own.my.constant.AttributeConstants.RANGE_OF_VALUES_MAXIMUM_BORDER;
import static en.ratings.own.my.constant.AttributeConstants.RANGE_OF_VALUES_MINIMUM_BORDER;
import static en.ratings.own.my.constant.AttributeConstants.STEP_WIDTH_BORDER;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRatingDTO {

    private String userId;

    private int maximumLengthOfName = MAXIMUM_LENGTH_OF_SMALL_STRING;

    private int maximumLengthOfDescription = MAXIMUM_LENGTH_OF_BIG_STRING;

    private double stepWidthBorder = STEP_WIDTH_BORDER;

    private int maximumNumberOfDecimalDigits = MAXIMUM_NUMBER_OF_DECIMAL_DIGITS;

    private double rangeOfValuesMaximumBorder = RANGE_OF_VALUES_MAXIMUM_BORDER;

    private double rangeOfValuesMinimumBorder = RANGE_OF_VALUES_MINIMUM_BORDER;

    public CreateRatingDTO(String userId) {
        setUserId(userId);
    }
}
