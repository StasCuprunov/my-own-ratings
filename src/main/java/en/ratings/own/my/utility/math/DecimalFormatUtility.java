package en.ratings.own.my.utility.math;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static en.ratings.own.my.constant.AttributeConstants.MAXIMUM_NUMBER_OF_DECIMAL_DIGITS;
import static en.ratings.own.my.utility.math.BigDecimalUtility.MINUS_ONE;
import static en.ratings.own.my.utility.math.BigDecimalUtility.isNumberEqualToZero;
import static en.ratings.own.my.utility.math.BigDecimalUtility.isNumberSmallerThanSmallestAllowedPositiveNumber;
import static en.ratings.own.my.utility.math.BigDecimalUtility.isNumberSmallerThanZero;
import static java.math.RoundingMode.DOWN;
import static java.util.Locale.ENGLISH;

public class DecimalFormatUtility {

    private static final DecimalFormat DECIMAL_FORMAT_FOR_CUTTING_DOUBLE = generateDecimalFormatForCuttingDouble();

    public static Double cutDoubleAfterMaximumNumberOfDecimalDigits(Double number) {
        return Double.valueOf(DECIMAL_FORMAT_FOR_CUTTING_DOUBLE.format(number));
    }

    public static boolean numberHasTooManyDecimalDigits(Double number) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(number));
        BigDecimal decimalPart = bigDecimal.subtract(new BigDecimal(bigDecimal.intValue()));
        if (isNumberEqualToZero(decimalPart)) {
            return false;
        }
        else if (isNumberSmallerThanZero(decimalPart)) {
            decimalPart = decimalPart.multiply(MINUS_ONE);
        }
        return isNumberSmallerThanSmallestAllowedPositiveNumber(decimalPart);
    }
    private static String generateDecimalFormatPattern() {
        String placeHolder = "#";
        String pattern = placeHolder;
        if (MAXIMUM_NUMBER_OF_DECIMAL_DIGITS < 1) {
            return pattern;
        }
        pattern += ".";
        for (int index = 0; index < MAXIMUM_NUMBER_OF_DECIMAL_DIGITS; index++) {
            pattern += placeHolder;
        }
        return pattern;
    }

    private static DecimalFormat generateDecimalFormatForCuttingDouble() {
        DecimalFormat decimalFormat = new DecimalFormat(generateDecimalFormatPattern());
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(ENGLISH));
        decimalFormat.setRoundingMode(DOWN);
        return decimalFormat;
    }
}
