package en.ratings.own.my.test.integration.controller.utility.rating;

import en.ratings.own.my.model.rating.RangeOfValues;

public class CreateRangeOfValuesUtility {
    public static RangeOfValues VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING =
            new RangeOfValues(1.0, 6.0, 1.0);

    public static RangeOfValues VALID_RANGE_OF_VALUES_AS_AMAZON_RATING =
            new RangeOfValues(0.0, 5.0, 0.5);

    public static RangeOfValues VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM =
            new RangeOfValues(-4.0, 0.0, 0.2);

    public static RangeOfValues VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM =
            new RangeOfValues(-12.3, -1.8, 2.1);

    public static RangeOfValues INVALID_RANGE_OF_VALUES_WITH_NEGATIVE_STEP_WIDTH =
            new RangeOfValues(VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING.getMinimum(),
                    VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING.getMaximum(), -0.1);

    public static RangeOfValues INVALID_RANGE_OF_VALUES_WITH_ZERO_STEP_WIDTH =
            new RangeOfValues(VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM.getMinimum(),
                    VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING.getMaximum(), 0.0);

    public static RangeOfValues INVALID_RANGE_OF_VALUES_WITH_MINIMUM_EQUALS_TO_MAXIMUM =
            new RangeOfValues(10.0, 10.0, 0.5);

    public static RangeOfValues INVALID_RANGE_OF_VALUES_WITH_MINIMUM_GREATER_THAN_MAXIMUM =
            new RangeOfValues(2.4, 1.2, 1.2);

    public static RangeOfValues INVALID_RANGE_OF_VALUES_WITH_UNAVAILABLE_MAXIMUM =
            new RangeOfValues(1.7, 712.0, 224.0);
}
