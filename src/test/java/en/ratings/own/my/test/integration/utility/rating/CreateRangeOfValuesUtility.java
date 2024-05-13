package en.ratings.own.my.test.integration.utility.rating;

import en.ratings.own.my.model.rating.RangeOfValues;

import static en.ratings.own.my.constant.AttributeConstants.MAXIMUM_NUMBER_OF_DECIMAL_DIGITS;
import static en.ratings.own.my.constant.AttributeConstants.RANGE_OF_VALUES_MAXIMUM_BORDER;
import static en.ratings.own.my.constant.AttributeConstants.RANGE_OF_VALUES_MINIMUM_BORDER;
import static en.ratings.own.my.utility.MathUtility.tenPow;

public class CreateRangeOfValuesUtility {
    public static RangeOfValues VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING =
            new RangeOfValues(1.0, 6.0, 1.0);

    public static RangeOfValues VALID_RANGE_OF_VALUES_AS_AMAZON_RATING =
            new RangeOfValues(0.0, 5.0, 0.5);

    public static RangeOfValues VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM =
            new RangeOfValues(-4.0, 0.0, 0.2);

    public static RangeOfValues VALID_RANGE_OF_VALUES_WITH_NEGATIVE_MINIMUM_AND_MAXIMUM =
            new RangeOfValues(-12.3, -1.8, 2.1);

    public static RangeOfValues INVALID_RANGE_OF_VALUES_WITH_TOO_SMALL_MINIMUM =
            new RangeOfValues(RANGE_OF_VALUES_MINIMUM_BORDER - 1,
                    VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING.getMaximum(),
                    VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING.getStepWidth());

    public static RangeOfValues INVALID_RANGE_OF_VALUES_WITH_TOO_BIG_MAXIMUM =
            new RangeOfValues(VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING.getMinimum(),
                    RANGE_OF_VALUES_MAXIMUM_BORDER + 1,
                    VALID_RANGE_OF_VALUES_AS_GERMAN_GRADING.getStepWidth());

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


    public static RangeOfValues createInvalidRangeOfValuesWithMinimumTooManyDecimalDigits() {
        Double minimum = createDecimalNumberWithTooManyDecimalDigits();
        Double maximum = 10 * (9 + minimum);
        Double stepWidth = 1.0;
        return new RangeOfValues(minimum, maximum, stepWidth);
    }

    public static RangeOfValues createInvalidRangeOfValuesWithMaximumAndStepWidthWithTooManyDecimalDigits() {
        Double minimum = 2.0;
        Double stepWidth = createDecimalNumberWithTooManyDecimalDigits();
        Double maximum = (10 * stepWidth) + minimum;
        return new RangeOfValues(minimum, maximum, stepWidth);
    }

    public static RangeOfValues createInvalidRangeOfValuesWithStepWidthTooManyDecimalDigits() {
        Double minimum = 2.0;
        Double stepWidth = createDecimalNumberWithTooManyDecimalDigits();
        Double maximum = (tenPow(MAXIMUM_NUMBER_OF_DECIMAL_DIGITS) * stepWidth) + minimum;
        return new RangeOfValues(minimum, maximum, stepWidth);
    }

    private static Double createDecimalNumberWithTooManyDecimalDigits() {
        return tenPow(-MAXIMUM_NUMBER_OF_DECIMAL_DIGITS) - tenPow(-(MAXIMUM_NUMBER_OF_DECIMAL_DIGITS + 1));
    }
}
