package en.ratings.own.my.exception.rating.creation;

import java.util.ArrayList;

public class RatingCreationFailedException extends Exception {
    public RatingCreationFailedException(ArrayList<String> keysForException) {
        super(new RatingCreationFailedErrorMessage().errorMessage(keysForException));
    }
}
