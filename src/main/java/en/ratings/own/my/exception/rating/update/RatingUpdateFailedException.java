package en.ratings.own.my.exception.rating.update;

import java.util.ArrayList;

public class RatingUpdateFailedException extends Exception {
    public RatingUpdateFailedException(ArrayList<String> keysForException) {
        super(new RatingUpdateFailedErrorMessage().errorMessage(keysForException));
    }

    public RatingUpdateFailedException(ArrayList<String> keysForException,
                                       ArrayList<String> ratingEntriesDontFitInScale) {
        super(new RatingUpdateFailedErrorMessage().errorMessageAfterCheckRangeOfValuesAndRatingId(keysForException,
                ratingEntriesDontFitInScale));
    }
}
