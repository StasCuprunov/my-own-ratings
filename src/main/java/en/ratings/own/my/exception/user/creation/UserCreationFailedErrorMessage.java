package en.ratings.own.my.exception.user.creation;

import en.ratings.own.my.exception.InterfaceErrorMessage;

import java.util.ArrayList;

import static en.ratings.own.my.constant.ExceptionConstants.KEY_EMAIL_SYNTAX;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_EMAIL_ALREADY_EXISTS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_EMAIL_TOO_LONG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_FIRST_NAME_TOO_LONG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_ID_IS_DEFINED;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_HAS_NO_DIGIT;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_HAS_NO_ENGLISH_LOWER_CASE_LETTER;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_HAS_NO_ENGLISH_UPPER_CASE_LETTER;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_HAS_NO_VALID_SPECIAL_CHARACTER;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_TOO_SHORT;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_TOO_LONG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_SURNAME_TOO_LONG;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForDefinedId;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForEmailSyntax;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForEmailAlreadyExists;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForEmailTooLong;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForFirstNameTooLong;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForPasswordHasNoDigit;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForPasswordHasNoEnglishLowerCaseLetter;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForPasswordHasNoEnglishUpperCaseLetter;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForPasswordHasNoValidSpecialCharacter;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForPasswordTooShort;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForPasswordTooLong;
import static en.ratings.own.my.exception.text.UserExceptionText.errorMessageForSurnameTooLong;
import static en.ratings.own.my.utility.StringUtility.makeText;

public class UserCreationFailedErrorMessage implements InterfaceErrorMessage {
    @Override
    public String errorMessage(ArrayList<String> keysForException) {
        ArrayList<String> errorMessage = new ArrayList<>();
        errorMessage.add(errorMessageBegin());

        for (String key : keysForException) {
            switch (key) {
                case KEY_ID_IS_DEFINED -> errorMessage.add(errorMessageForDefinedId());
                case KEY_EMAIL_SYNTAX -> errorMessage.add(errorMessageForEmailSyntax());
                case KEY_EMAIL_TOO_LONG -> errorMessage.add(errorMessageForEmailTooLong());
                case KEY_EMAIL_ALREADY_EXISTS -> errorMessage.add(errorMessageForEmailAlreadyExists());
                case KEY_FIRST_NAME_TOO_LONG -> errorMessage.add(errorMessageForFirstNameTooLong());
                case KEY_SURNAME_TOO_LONG -> errorMessage.add(errorMessageForSurnameTooLong());
                case KEY_PASSWORD_TOO_SHORT -> errorMessage.add(errorMessageForPasswordTooShort());
                case KEY_PASSWORD_TOO_LONG -> errorMessage.add(errorMessageForPasswordTooLong());
                case KEY_PASSWORD_HAS_NO_DIGIT -> errorMessage.add(errorMessageForPasswordHasNoDigit());
                case KEY_PASSWORD_HAS_NO_ENGLISH_UPPER_CASE_LETTER ->
                        errorMessage.add(errorMessageForPasswordHasNoEnglishUpperCaseLetter());
                case KEY_PASSWORD_HAS_NO_ENGLISH_LOWER_CASE_LETTER ->
                    errorMessage.add(errorMessageForPasswordHasNoEnglishLowerCaseLetter());
                case KEY_PASSWORD_HAS_NO_VALID_SPECIAL_CHARACTER ->
                    errorMessage.add(errorMessageForPasswordHasNoValidSpecialCharacter());
            }
        }
        return makeText(errorMessage);
    }

    @Override
    public String errorMessageBegin() {
        return "User creation failed:";
    }
}
