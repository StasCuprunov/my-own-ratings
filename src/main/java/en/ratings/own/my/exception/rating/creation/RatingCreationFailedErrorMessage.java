package en.ratings.own.my.exception.rating.creation;

import en.ratings.own.my.exception.InterfaceErrorMessage;

import java.util.ArrayList;

import static en.ratings.own.my.constant.ExceptionConstants.KEY_USER_WITH_ID_NOT_FOUND;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_MINIMUM_IS_NOT_VALID;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_STEP_WIDTH_IS_TOO_SMALL;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RANGE_OF_VALUES_IS_NOT_CONSISTENT;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_NAME_ALREADY_USED_FOR_USER;

import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForUserWithIdNotFound;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForMinimumIsNotValid;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForStepWidthIsTooSmall;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForRangeOfValuesIsNotConsistent;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForRatingNameAlreadyUsedForUser;

import static en.ratings.own.my.utility.StringUtility.makeText;

public class RatingCreationFailedErrorMessage implements InterfaceErrorMessage {
    @Override
    public String errorMessage(ArrayList<String> keysForException) {
        ArrayList<String> errorMessage = new ArrayList<>();
        errorMessage.add(errorMessageBegin());

        for (String key : keysForException) {
            switch (key) {
                case KEY_USER_WITH_ID_NOT_FOUND -> errorMessage.add(errorMessageForUserWithIdNotFound());
                case KEY_MINIMUM_IS_NOT_VALID -> errorMessage.add(errorMessageForMinimumIsNotValid());
                case KEY_STEP_WIDTH_IS_TOO_SMALL -> errorMessage.add(errorMessageForStepWidthIsTooSmall());
                case KEY_RANGE_OF_VALUES_IS_NOT_CONSISTENT -> errorMessage.
                        add(errorMessageForRangeOfValuesIsNotConsistent());
                case KEY_RATING_NAME_ALREADY_USED_FOR_USER -> errorMessage.
                        add(errorMessageForRatingNameAlreadyUsedForUser());
            }
        }
        return makeText(errorMessage);
    }

    @Override
    public String errorMessageBegin() {
        return "Rating creation failed:";
    }
}
