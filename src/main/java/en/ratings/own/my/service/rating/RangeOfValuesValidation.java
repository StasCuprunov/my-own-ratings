package en.ratings.own.my.service.rating;

import en.ratings.own.my.model.rating.RangeOfValues;

import java.util.ArrayList;

import static en.ratings.own.my.constant.AttributeConstants.STEP_WIDTH_BORDER;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MINIMUM_IS_NOT_VALID;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RANGE_OF_VALUES_IS_NOT_CONSISTENT;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_STEP_WIDTH_IS_TOO_SMALL;

public class RangeOfValuesValidation {

    public static ArrayList<String> rangeOfValuesValidation(RangeOfValues rangeOfValues) {
        ArrayList<String> keysForException = new ArrayList<>();
        if (rangeOfValues.getMinimum() >= rangeOfValues.getMaximum()) {
            keysForException.add(KEY_MINIMUM_IS_NOT_VALID);
        }
        if (rangeOfValues.getStepWidth() < STEP_WIDTH_BORDER) {
            keysForException.add(KEY_STEP_WIDTH_IS_TOO_SMALL);
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
        if (rangeOfValues.getMaximum() < value) {
            return false;
        }
        return canValueBeReachedFromMinimum(value, rangeOfValues.getMinimum(), rangeOfValues.getStepWidth());
    }

    private static boolean canValueBeReachedFromMinimum(Double value, Double minimum, Double stepWidth) {
        return ((value - minimum) % stepWidth) == 0;
    }
}
