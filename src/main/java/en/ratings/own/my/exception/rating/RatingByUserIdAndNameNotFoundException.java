package en.ratings.own.my.exception.rating;

public class RatingByUserIdAndNameNotFoundException extends Exception {
    public RatingByUserIdAndNameNotFoundException(String userId, String name) {
        super(errorMessage(userId, name));
    }

    private static String errorMessage(String userId, String name) {
        return "Rating by userId " + userId + " and name " + name + " could not be found.";
    }
}
