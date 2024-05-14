package en.ratings.own.my.exception.rating.update;

public class RatingUpdateNotAllowedException extends Exception {
    public RatingUpdateNotAllowedException(String id) {
        super(errorMessage(id));
    }

    private static String errorMessage(String id) {
        return "You are unauthorized to edit the rating with the id " + id + ".";
    }
}
