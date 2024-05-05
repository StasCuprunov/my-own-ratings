package en.ratings.own.my.exception.rating;

public class RatingsByUserIdNotFoundException extends Exception {
    public RatingsByUserIdNotFoundException(Long userId) {
        super(errorMessage(userId));
    }

    private static String errorMessage(Long userId) {
        return "No ratings by user id " + userId + " could be found.";
    }
}
