package en.ratings.own.my.service.rating;

import en.ratings.own.my.model.rating.RangeOfValues;

import java.math.BigDecimal;
import java.util.ArrayList;

import static en.ratings.own.my.constant.AttributeConstants.RANGE_OF_VALUES_MAXIMUM_BORDER;
import static en.ratings.own.my.constant.AttributeConstants.RANGE_OF_VALUES_MINIMUM_BORDER;
import static en.ratings.own.my.constant.AttributeConstants.STEP_WIDTH_BORDER;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MAXIMUM_HAS_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MAXIMUM_IS_TOO_BIG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MINIMUM_HAS_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MINIMUM_IS_TOO_BIG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MINIMUM_IS_TOO_SMALL;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RANGE_OF_VALUES_IS_NOT_CONSISTENT;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_STEP_WIDTH_HAS_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_STEP_WIDTH_IS_TOO_SMALL;
import static en.ratings.own.my.utility.math.BigDecimalUtility.isNumberEqualToZero;
import static en.ratings.own.my.utility.math.DecimalFormatUtility.cutDoubleAfterMaximumNumberOfDecimalDigits;
import static en.ratings.own.my.utility.math.DecimalFormatUtility.numberHasTooManyDecimalDigits;
import static java.math.BigDecimal.ZERO;

/**
 * Use BigDecimal for calculation
 */
public class RangeOfValuesValidation {

    public static ArrayList<String> rangeOfValuesValidation(RangeOfValues rangeOfValues) {
        ArrayList<String> keysForException = new ArrayList<>(minimumValidation(rangeOfValues));
        keysForException.addAll(maximumValidation(rangeOfValues));
        keysForException.addAll(stepWidthValidation(rangeOfValues));
        if (!keysForException.isEmpty()) {
            return keysForException;
        }
        if (!isRangeOfValuesConsistent(rangeOfValues)) {
            keysForException.add(KEY_RANGE_OF_VALUES_IS_NOT_CONSISTENT);
        }
        return keysForException;
    }

    public static boolean isRangeOfValuesConsistent(RangeOfValues rangeOfValues) {
        return canValueBeReachedFromMinimum(rangeOfValues.getMaximum(), rangeOfValues.getMinimum(),
                rangeOfValues.getStepWidth());
    }

    public static boolean isValueInRangeOfValues(Double value, RangeOfValues rangeOfValues) {
        Double minimum = rangeOfValues.getMinimum();
        if ((rangeOfValues.getMaximum() < value) || (minimum > value)) {
            return false;
        }
        return canValueBeReachedFromMinimum(value, minimum, rangeOfValues.getStepWidth());
    }

    private static ArrayList<String> minimumValidation(RangeOfValues rangeOfValues) {
        ArrayList<String> keysForException = new ArrayList<>();
        Double minimum = rangeOfValues.getMinimum();
        if (minimum < RANGE_OF_VALUES_MINIMUM_BORDER) {
            keysForException.add(KEY_MINIMUM_IS_TOO_SMALL);
        }
        if (minimum >= rangeOfValues.getMaximum()) {
            keysForException.add(KEY_MINIMUM_IS_TOO_BIG);
        }
        if (numberHasTooManyDecimalDigits(minimum)) {
            keysForException.add(KEY_MINIMUM_HAS_TOO_MANY_DECIMAL_DIGITS);
        }
        return keysForException;
    }

    private static ArrayList<String> maximumValidation(RangeOfValues rangeOfValues) {
        Double maximum = rangeOfValues.getMaximum();
        ArrayList<String> keysForException = new ArrayList<>();
        if (maximum > RANGE_OF_VALUES_MAXIMUM_BORDER) {
            keysForException.add(KEY_MAXIMUM_IS_TOO_BIG);
        }
        if (numberHasTooManyDecimalDigits(maximum)) {
            keysForException.add(KEY_MAXIMUM_HAS_TOO_MANY_DECIMAL_DIGITS);
        }
        return keysForException;
    }

    private static ArrayList<String> stepWidthValidation(RangeOfValues rangeOfValues) {
        Double stepWidth = rangeOfValues.getStepWidth();
        ArrayList<String> keysForException = new ArrayList<>();
        if (stepWidth <= STEP_WIDTH_BORDER) {
            keysForException.add(KEY_STEP_WIDTH_IS_TOO_SMALL);
        }
        if (numberHasTooManyDecimalDigits(stepWidth)) {
            keysForException.add(KEY_STEP_WIDTH_HAS_TOO_MANY_DECIMAL_DIGITS);
        }
        return keysForException;
    }

    private static boolean canValueBeReachedFromMinimum(Double value, Double minimum, Double stepWidth) {
        BigDecimal stepWidthBigDecimal = BigDecimal.valueOf(cutDoubleAfterMaximumNumberOfDecimalDigits(stepWidth));
        if (isNumberEqualToZero(stepWidthBigDecimal)) {
            return false;
        }
        BigDecimal valueBigDecimal = BigDecimal.valueOf(cutDoubleAfterMaximumNumberOfDecimalDigits(value));
        BigDecimal minimumBigDecimal = BigDecimal.valueOf(cutDoubleAfterMaximumNumberOfDecimalDigits(minimum));
        BigDecimal distance = valueBigDecimal.subtract(minimumBigDecimal);
        return distance.remainder(stepWidthBigDecimal).compareTo(ZERO) == 0;
    }

}
