package en.ratings.own.my.exception.rating.range.of.values;

public class RangeOfValuesByIdNotFoundException extends Exception {
    public RangeOfValuesByIdNotFoundException(String id) {
        super (errorMessage(id));
    }

    private static String errorMessage(String id) {
        return "Range of values with id " + id + " could not be found.";
    }
}
