package en.ratings.own.my.exception.rating.entry;

import en.ratings.own.my.exception.InterfaceErrorMessage;

import java.util.ArrayList;

import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRY_BY_ID_NOT_FOUND;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_BY_ID_NOT_FOUND;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRY_NAME_ALREADY_USED_IN_RATING;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRY_NAME_IS_EMPTY;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_RATING_ENTRY_VALUE_IS_NOT_ALLOWED;
import static en.ratings.own.my.exception.text.RatingEntryExceptionText.errorMessageForRatingEntryNameIsEmpty;
import static en.ratings.own.my.exception.text.RatingEntryExceptionText.errorMessageRatingEntryByIdNotFound;
import static en.ratings.own.my.exception.text.RatingEntryExceptionText.errorMessageRatingEntryNameAlreadyUsedInRating;
import static en.ratings.own.my.exception.text.RatingEntryExceptionText.errorMessageRatingEntryValueIsNotAllowed;
import static en.ratings.own.my.exception.text.RatingExceptionText.errorMessageForRatingByIdNotFound;
import static en.ratings.own.my.utility.StringUtility.makeText;

public class RatingEntryFailedErrorMessage implements InterfaceErrorMessage {
    @Override
    public String errorMessage(ArrayList<String> keysForException) {
        ArrayList<String> errorMessage = new ArrayList<>();
        errorMessage.add(errorMessageBegin());

        for (String key : keysForException) {
            switch (key) {
                case KEY_RATING_ENTRY_BY_ID_NOT_FOUND -> errorMessage.add(errorMessageRatingEntryByIdNotFound());
                case KEY_RATING_BY_ID_NOT_FOUND -> errorMessage.add(errorMessageForRatingByIdNotFound());
                case KEY_RATING_ENTRY_NAME_IS_EMPTY -> errorMessage.add(errorMessageForRatingEntryNameIsEmpty());
                case KEY_RATING_ENTRY_NAME_ALREADY_USED_IN_RATING -> errorMessage.
                        add(errorMessageRatingEntryNameAlreadyUsedInRating());
                case KEY_RATING_ENTRY_VALUE_IS_NOT_ALLOWED -> errorMessage.
                        add(errorMessageRatingEntryValueIsNotAllowed());
            }
        }
        return makeText(errorMessage);
    }

    @Override
    public String errorMessageBegin() {
        return "Rating entry creation/update failed:";
    }
}
