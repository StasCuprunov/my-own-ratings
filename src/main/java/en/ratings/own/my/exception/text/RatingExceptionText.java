package en.ratings.own.my.exception.text;

import java.util.ArrayList;

import static en.ratings.own.my.constant.AttributeConstants.STEP_WIDTH_BORDER;
import static en.ratings.own.my.utility.StringUtility.enumerateStrings;

public class RatingExceptionText {

    public static String errorMessageForRatingByIdNotFound() {
        return "The rating with the specified id does not exist.";
    }
    public static String errorMessageForUserWithIdNotFound() {
        return "The user with the specified id does not exist.";
    }

    public static String errorMessageForMinimumIsNotValid() {
        return "The minimum must be really smaller than the maximum.";
    }

    public static String errorMessageForStepWidthIsTooSmall() {
        return "The step width must be really greater than " + STEP_WIDTH_BORDER + ".";
    }

    public static String errorMessageForRangeOfValuesIsNotConsistent() {
        return "The range of values must be a scale.";
    }

    public static String errorMessageForRatingNameAlreadyUsedForUser() {
        return "The rating name is already used by you. This name has to be unique.";
    }

    public static String errorMessageForRatingEntriesDontFitInScale(ArrayList<String> ratingEntriesDontFitInScale) {
        String messageBegin = "The new range of scale isn't allowed " +
                "because for the following rating entries the new range of values doesn't fit: ";
        String enumerationRatingEntries = enumerateStrings(ratingEntriesDontFitInScale);

        return messageBegin + enumerationRatingEntries + ".";
    }
}
