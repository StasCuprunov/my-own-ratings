package en.ratings.own.my.test.utility;

import en.ratings.own.my.model.rating.RangeOfValues;

import static en.ratings.own.my.utility.StringUtility.EMPTY_STRING;
import static org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class GeneratorUtility {
    public static final String ID_TEST = "test";

    public static String createNotExistentId(String existentId) {
        return existentId +  ID_TEST;
    }

    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static Double numberBetweenRangeOfValuesButNotAllowed(RangeOfValues rangeOfValues) {
        return rangeOfValues.getMinimum() + rangeOfValues.getStepWidth() / 2.0;
    }

    public static Double numberSmallerThanMinimum(Double minimum) {
        return minimum - 1;
    }

    public static Double numberGreaterThanMaximum(Double maximum) {
        return maximum + 1;
    }

    public static String generateRandomAlphabeticString(int numberOfCharacters) {
        if (numberOfCharacters <= 0) {
            return EMPTY_STRING;
        }
        return randomAlphabetic(numberOfCharacters);
    }

    public static String generateTooLongString(int maximumLength, String actualString) {
        int numberOfAddedCharacters = maximumLength - actualString.length() + 1;

        return actualString + generateRandomAlphabeticString(numberOfAddedCharacters);
    }
}
