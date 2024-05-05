package en.ratings.own.my.exception.text;

public class RatingEntryExceptionText {
    public static String errorMessageRatingEntryNameAlreadyUsedInRating() {
        return "The rating entry name is already used in your rating. This name has to be unique for every rating.";
    }
    public static String errorMessageRatingEntryValueIsNotAllowed() {
        return "The value is not allowed for the rating entry.";
    }
}
