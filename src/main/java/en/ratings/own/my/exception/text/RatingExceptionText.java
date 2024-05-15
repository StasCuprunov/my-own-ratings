package en.ratings.own.my.exception.text;

import java.util.ArrayList;

import static en.ratings.own.my.constant.AttributeConstants.MAXIMUM_NUMBER_OF_DECIMAL_DIGITS;
import static en.ratings.own.my.constant.AttributeConstants.RANGE_OF_VALUES_MAXIMUM_BORDER;
import static en.ratings.own.my.constant.AttributeConstants.RANGE_OF_VALUES_MINIMUM_BORDER;
import static en.ratings.own.my.constant.AttributeConstants.STEP_WIDTH_BORDER;
import static en.ratings.own.my.utility.StringUtility.enumerateStrings;

public class RatingExceptionText {

    public static String errorMessageForRatingByIdNotFound() {
        return "The rating with the specified id does not exist.";
    }
    public static String errorMessageForUserWithIdNotFound() {
        return "The user with the specified id does not exist.";
    }

    public static String errorMessageForMinimumIsTooBig() {
        return "The minimum must be really smaller than the maximum.";
    }

    public static String errorMessageForMinimumIsTooSmall() {
        return "The minimum must be greater or equal " + RANGE_OF_VALUES_MINIMUM_BORDER;
    }

    public static String errorMessageForMinimumHasTooManyDecimalDigits() {
        return errorMessageForNumberHasTooManyDecimalDigits("minimum");
    }

    public static String errorMessageForMaximumIsTooBig() {
        return "The maximum must be smaller or equal " + RANGE_OF_VALUES_MAXIMUM_BORDER;
    }

    public static String errorMessageForMaximumHasTooManyDecimalDigits() {
        return errorMessageForNumberHasTooManyDecimalDigits("maximum");
    }

    public static String errorMessageForStepWidthIsTooSmall() {
        return "The step width must be really greater than " + STEP_WIDTH_BORDER + ".";
    }

    public static String errorMessageForStepWidthHasTooManyDecimalDigits() {
        return errorMessageForNumberHasTooManyDecimalDigits("step width");
    }

    public static String errorMessageForRangeOfValuesIsNotConsistent() {
        return "The range of values must be a scale.";
    }

    public static String errorMessageForRatingNameIsEmpty() {
        return "The rating name may not be empty.";
    }
    public static String errorMessageForRatingNameAlreadyUsedForUser() {
        return "The rating name is already used by you. This name has to be unique.";
    }

    public static String errorMessageForRatingHasDefinedId() {
        return "The rating may not have a defined id.";
    }

    public static String errorMessageForRatingEntriesDontFitInScale(ArrayList<String> ratingEntriesDontFitInScale) {
        String messageBegin = "The new range of scale isn't allowed " +
                "because for the following rating entries the new range of values doesn't fit: ";
        String enumerationRatingEntries = enumerateStrings(ratingEntriesDontFitInScale);

        return messageBegin + enumerationRatingEntries + ".";
    }

    private static String errorMessageForNumberHasTooManyDecimalDigits(String numberName) {
        return "Only " + numberName + " with maximal " + MAXIMUM_NUMBER_OF_DECIMAL_DIGITS +
                " digits after decimal point is allowed.";
    }
}
