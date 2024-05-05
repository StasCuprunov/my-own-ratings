package en.ratings.own.my.exception.rating.entry;

public class RatingEntryByIdNotFoundException extends Exception {
    public RatingEntryByIdNotFoundException(Long id) {
        super(errorMessage(id));
    }

    private static String errorMessage(Long id) {
        return "The rating entry with id " + id + " could not be found.";
    }
}
