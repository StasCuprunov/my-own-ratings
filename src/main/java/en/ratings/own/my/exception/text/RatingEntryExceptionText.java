package en.ratings.own.my.exception.text;

public class RatingEntryExceptionText {

    public static String errorMessageRatingEntryByIdNotFound() {
        return "The rating entry with the specified id could not be found.";
    }

    public static String errorMessageForRatingEntryNameIsEmpty() {
        return "The rating entry name may not be empty.";
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
