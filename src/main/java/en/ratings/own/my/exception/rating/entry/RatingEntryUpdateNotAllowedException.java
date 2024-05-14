package en.ratings.own.my.exception.rating.entry;

public class RatingEntryUpdateNotAllowedException extends Exception {
    public RatingEntryUpdateNotAllowedException(String id) {
        super(errorMessage(id));
    }

    private static String errorMessage(String id) {
        return "You are not allowed to update the rating entry with id " + id + ".";
    }
}
