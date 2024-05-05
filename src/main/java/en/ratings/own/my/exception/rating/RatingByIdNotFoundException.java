package en.ratings.own.my.exception.rating;

public class RatingByIdNotFoundException extends Exception {
    public RatingByIdNotFoundException(Long id) {
        super(errorMessageForRatingByIdNotFound(id));
    }

    private static String errorMessageForRatingByIdNotFound(Long id) {
        return "Rating with id " + id + " could not be found.";
    }
}
