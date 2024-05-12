package en.ratings.own.my.exception.rating;

public class RatingDeleteByIdNotAllowedException extends Exception {
    public RatingDeleteByIdNotAllowedException(String id) {
        super(errorMessage(id));
    }

    private static String errorMessage(String id) {
        return "You are not allowed to delete the rating with the id " + id + ".";
    }
}
