package en.ratings.own.my.exception.rating.update;

import en.ratings.own.my.exception.InterfaceErrorMessage;

import java.util.ArrayList;

import static en.ratings.own.my.constant.ExceptionConstants.KEY_MAXIMUM_HAS_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MAXIMUM_IS_TOO_BIG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MINIMUM_HAS_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MINIMUM_IS_TOO_BIG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MINIMUM_IS_TOO_SMALL;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_DESCRIPTION_TOO_LONG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_NAME_IS_EMPTY;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_NAME_IS_TOO_LONG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_STEP_WIDTH_HAS_TOO_MANY_DECIMAL_DIGITS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_STEP_WIDTH_IS_TOO_SMALL;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RANGE_OF_VALUES_IS_NOT_CONSISTENT;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_BY_ID_NOT_FOUND;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_NAME_ALREADY_USED_FOR_USER;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRIES_DONT_FIT_IN_SCALE;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForMaximumHasTooManyDecimalDigits;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForMaximumIsTooBig;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForMinimumHasTooManyDecimalDigits;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForMinimumIsTooBig;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForMinimumIsTooSmall;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForRatingDescriptionIsTooLong;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForRatingNameIsEmpty;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForRatingNameIsTooLong;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForStepWidthHasTooManyDecimalDigits;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForStepWidthIsTooSmall;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForRangeOfValuesIsNotConsistent;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForRatingNameAlreadyUsedForUser;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForRatingEntriesDontFitInScale;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForRatingByIdNotFound;
import static en.ratings.own.my.utility.StringUtility.makeText;

public class RatingUpdateFailedErrorMessage implements InterfaceErrorMessage {
    @Override
    public String errorMessage(ArrayList<String> keysForException) {
        ArrayList<String> errorMessage = new ArrayList<>();
        errorMessage.add(errorMessageBegin());

        for (String key : keysForException) {
            switch (key) {
                case KEY_MINIMUM_IS_TOO_BIG -> errorMessage.add(errorMessageForMinimumIsTooBig());
                case KEY_MINIMUM_IS_TOO_SMALL -> errorMessage.add(errorMessageForMinimumIsTooSmall());
                case KEY_MINIMUM_HAS_TOO_MANY_DECIMAL_DIGITS ->
                        errorMessage.add(errorMessageForMinimumHasTooManyDecimalDigits());
                case KEY_MAXIMUM_IS_TOO_BIG ->  errorMessage.add(errorMessageForMaximumIsTooBig());
                case KEY_MAXIMUM_HAS_TOO_MANY_DECIMAL_DIGITS ->
                        errorMessage.add(errorMessageForMaximumHasTooManyDecimalDigits());
                case KEY_STEP_WIDTH_IS_TOO_SMALL -> errorMessage.add(errorMessageForStepWidthIsTooSmall());
                case KEY_STEP_WIDTH_HAS_TOO_MANY_DECIMAL_DIGITS ->
                        errorMessage.add(errorMessageForStepWidthHasTooManyDecimalDigits());
                case KEY_RANGE_OF_VALUES_IS_NOT_CONSISTENT -> errorMessage.
                        add(errorMessageForRangeOfValuesIsNotConsistent());
                case KEY_RATING_BY_ID_NOT_FOUND -> errorMessage.add(errorMessageForRatingByIdNotFound());
            }
        }
        return makeText(errorMessage);
    }

    public String errorMessageAfterCheckRangeOfValuesAndRatingId(ArrayList<String> keysForException,
                                                                 ArrayList<String> ratingEntriesDontFitInScale) {
        ArrayList<String> errorMessage = new ArrayList<>();
        errorMessage.add(errorMessageBegin());

        for (String key : keysForException) {
            switch (key) {
                case KEY_RATING_NAME_ALREADY_USED_FOR_USER -> errorMessage.
                        add(errorMessageForRatingNameAlreadyUsedForUser());
                case KEY_RATING_NAME_IS_EMPTY -> errorMessage.add(errorMessageForRatingNameIsEmpty());
                case KEY_RATING_NAME_IS_TOO_LONG -> errorMessage.add(errorMessageForRatingNameIsTooLong());
                case KEY_RATING_DESCRIPTION_TOO_LONG -> errorMessage.add(errorMessageForRatingDescriptionIsTooLong());
                case KEY_RATING_ENTRIES_DONT_FIT_IN_SCALE -> errorMessage.
                        add(errorMessageForRatingEntriesDontFitInScale(ratingEntriesDontFitInScale));
            }
        }
        return makeText(errorMessage);
    }

    @Override
    public String errorMessageBegin() {
        return "Rating update failed:";
    }
}
