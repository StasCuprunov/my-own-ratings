package en.ratings.own.my.service.rating;

import en.ratings.own.my.model.rating.RangeOfValues;

public class RangeOfValuesValidation {

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

    public static boolean canValueBeReachedFromMinimum(Double value, Double minimum, Double stepWidth) {
        return ((value - minimum) % stepWidth) == 0;
    }
}
