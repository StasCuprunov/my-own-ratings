package en.ratings.own.my.exception.rating.entry;

public class RatingEntryCreateNotAllowedException extends Exception {
    public RatingEntryCreateNotAllowedException(String ratingId) {
        super(errorMessage(ratingId));
    }

    private static String errorMessage(String ratingId) {
        return "You are not allowed to create a rating entry for the rating id " + ratingId + ".";
    }
}
