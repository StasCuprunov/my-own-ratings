package en.ratings.own.my.exception.rating;

public class RatingByIdNotAllowedException extends Exception {
    public RatingByIdNotAllowedException(String id) {
        super(errorMessage(id));
    }

    private static String errorMessage(String id) {
        return "You are not allowed to access to the rating with the id " + id + ".";
    }
}
