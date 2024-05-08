package en.ratings.own.my.exception.rating.range.of.values;

import en.ratings.own.my.model.rating.RangeOfValues;

public class RangeOfValuesByMinimumAndMaximumAndStepWidthNotFoundException extends Exception {
    public RangeOfValuesByMinimumAndMaximumAndStepWidthNotFoundException(RangeOfValues rangeOfValues) {
        super(errorMessage(rangeOfValues));
    }

    private static String errorMessage(RangeOfValues rangeOfValues) {
        return "Range of values with minimum " + rangeOfValues.getMinimum() + ", maximum " + rangeOfValues.getMinimum()
                + " and step width " + rangeOfValues.getStepWidth() + " could not be found.";
    }
}
