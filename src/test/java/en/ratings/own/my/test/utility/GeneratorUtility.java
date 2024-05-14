package en.ratings.own.my.test.utility;

import en.ratings.own.my.model.rating.RangeOfValues;

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
}
