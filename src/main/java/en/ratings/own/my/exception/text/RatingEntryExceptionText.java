package en.ratings.own.my.exception.text;

import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_NAME;
import static en.ratings.own.my.exception.text.ExceptionTextUtility.errorMessageStringAttributeIsTooLong;

public class RatingEntryExceptionText {

    public static String errorMessageRatingEntryByIdNotFound() {
        return "The rating entry with the specified id could not be found.";
    }

    public static String errorMessageForRatingEntryNameIsEmpty() {
        return "The rating entry name may not be empty.";
    }
    public static String errorMessageForRatingEntryNameIsTooLong() {
        return errorMessageStringAttributeIsTooLong("rating entry name", MAX_LENGTH_OF_NAME);
    }
    public static String errorMessageRatingEntryNameAlreadyUsedInRating() {
        return "The rating entry name is already used in your rating. This name has to be unique for every rating.";
    }
    public static String errorMessageForRatingEntryHasTooManyDecimalDigits() {
        return "The rating entry value has too many decimal digits.";
    }
    public static String errorMessageRatingEntryValueDoesntFitInRangeOfValues() {
        return "The rating entry value doesn't fit in range of values.";
    }
}
