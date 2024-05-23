package en.ratings.own.my.constant;

import static en.ratings.own.my.utility.math.MathUtility.twoPow;

public class AttributeConstants {
    public static final long ADAPT_GMT_TIME_TO_GERMANY_TIME_IN_SECONDS = 7200; // 2 hours

    public static final Double STEP_WIDTH_BORDER = 0.0;

    public static final int MAXIMUM_NUMBER_OF_DECIMAL_DIGITS = 3;

    public static final Double RANGE_OF_VALUES_MAXIMUM_BORDER = 1000000.0;

    public static final Double RANGE_OF_VALUES_MINIMUM_BORDER = -RANGE_OF_VALUES_MAXIMUM_BORDER;

    public static final long EXPIRATION_TIME_IN_SECONDS = 86400 + ADAPT_GMT_TIME_TO_GERMANY_TIME_IN_SECONDS; // 1 day

    public static final int MAXIMUM_LENGTH_OF_SMALL_String = (int) twoPow(6);

    public static final int MAXIMUM_LENGTH_OF_BIG_STRING = (int) twoPow(10);
}
