package en.ratings.own.my.utility.math;

import java.math.BigDecimal;

import static en.ratings.own.my.constant.AttributeConstants.MAXIMUM_NUMBER_OF_DECIMAL_DIGITS;
import static java.math.BigDecimal.ZERO;

public class BigDecimalUtility {

    public static final BigDecimal MINUS_ONE = new BigDecimal("-1");

    public static final BigDecimal DECIMAL_PRECISION_INVERTED = BigDecimal.valueOf(Math.
            pow(10, MAXIMUM_NUMBER_OF_DECIMAL_DIGITS));

    public static boolean isNumberEqualToZero(BigDecimal number) {
        return number.compareTo(ZERO) == 0;
    }

    public static boolean isNumberSmallerThanZero(BigDecimal number) {
        return number.compareTo(ZERO) < 0;
    }

    public static boolean isNumberSmallerThanSmallestAllowedPositiveNumber(BigDecimal number) {
        BigDecimal numberToFindNotAllowedDecimalDigits = number.multiply(DECIMAL_PRECISION_INVERTED);
        return hasDecimalDigit(numberToFindNotAllowedDecimalDigits);
    }

    public static boolean hasDecimalDigit(BigDecimal number) {
        return number.subtract(BigDecimal.valueOf(number.intValue())).compareTo(ZERO) != 0;
    }

}
