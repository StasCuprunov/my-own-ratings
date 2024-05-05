package en.ratings.own.my.exception.rating.entry;

public class RatingEntriesByRatingIdNotFoundException extends Exception {
    public RatingEntriesByRatingIdNotFoundException(Long ratingId) {
        super(errorMessage(ratingId));
    }

    private static String errorMessage(Long ratingId) {
        return "No rating entries with rating id " + ratingId + "could be found.";
    }
}
