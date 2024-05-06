package en.ratings.own.my.exception.rating;

public class RatingByIdNotFoundException extends Exception {
    public RatingByIdNotFoundException(String id) {
        super(errorMessageForRatingByIdNotFound(id));
    }

    private static String errorMessageForRatingByIdNotFound(String id) {
        return "Rating with id " + id + " could not be found.";
    }
}
