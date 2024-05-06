package en.ratings.own.my.exception.rating.entry;

import java.util.ArrayList;

public class RatingEntryFailedException extends Exception {
    public RatingEntryFailedException(ArrayList<String> keysForException) {
        super(new RatingEntryFailedErrorMessage().errorMessage(keysForException));
    }
}
