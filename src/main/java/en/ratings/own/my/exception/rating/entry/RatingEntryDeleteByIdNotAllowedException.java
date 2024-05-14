package en.ratings.own.my.exception.rating.entry;

public class RatingEntryDeleteByIdNotAllowedException extends Exception {
    public RatingEntryDeleteByIdNotAllowedException(String id) {
        super(errorMessage(id));
    }

    private static String errorMessage(String id) {
        return "You are not allowed to delete the rating entry with the id " + id + ".";
    }
}
