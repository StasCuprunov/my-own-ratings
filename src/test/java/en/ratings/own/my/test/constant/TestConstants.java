package en.ratings.own.my.test.constant;

import static en.ratings.own.my.constant.AttributeConstants.MAXIMUM_NUMBER_OF_DECIMAL_DIGITS;
import static en.ratings.own.my.utility.math.MathUtility.tenPow;

public class TestConstants {

    public static final int EXPECTED_ONE = 1;

    public static final int EXPECTED_ZERO = 0;

    public static String EMPTY_TEXT = "    \t   \n ";

    public static double NUMBER_WITH_TOO_MANY_DECIMAL_DIGITS = 9 * (tenPow(-MAXIMUM_NUMBER_OF_DECIMAL_DIGITS) +
            tenPow(-(MAXIMUM_NUMBER_OF_DECIMAL_DIGITS + 1)));

}
