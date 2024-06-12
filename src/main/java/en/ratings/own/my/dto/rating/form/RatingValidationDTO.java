package en.ratings.own.my.dto.rating.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static en.ratings.own.my.constant.AttributeConstants.MAXIMUM_NUMBER_OF_DECIMAL_DIGITS;
import static en.ratings.own.my.constant.AttributeConstants.RANGE_OF_VALUES_MAXIMUM_BORDER;
import static en.ratings.own.my.constant.AttributeConstants.RANGE_OF_VALUES_MINIMUM_BORDER;
import static en.ratings.own.my.constant.AttributeConstants.STEP_WIDTH_BORDER;
import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_DESCRIPTION;
import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_NAME;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RatingValidationDTO {

    private String userId;

    private int maximumLengthOfName = MAX_LENGTH_OF_NAME;

    private int maximumLengthOfDescription = MAX_LENGTH_OF_DESCRIPTION;

    private double stepWidthBorder = STEP_WIDTH_BORDER;

    private int maximumNumberOfDecimalDigits = MAXIMUM_NUMBER_OF_DECIMAL_DIGITS;

    private double rangeOfValuesMaximumBorder = RANGE_OF_VALUES_MAXIMUM_BORDER;

    private double rangeOfValuesMinimumBorder = RANGE_OF_VALUES_MINIMUM_BORDER;

    public RatingValidationDTO(String userId) {
        setUserId(userId);
    }
}
