package en.ratings.own.my.exception.text;

import java.util.ArrayList;

import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_EMAIL;
import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_FIRST_NAME;
import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_SURNAME;
import static en.ratings.own.my.exception.text.ExceptionTextUtility.errorMessageStringAttributeIsTooLong;
import static en.ratings.own.my.utility.PasswordUtility.LIST_OF_VALID_SPECIAL_CHARACTERS;
import static en.ratings.own.my.utility.PasswordUtility.PASSWORD_MAXIMUM_LENGTH;
import static en.ratings.own.my.utility.PasswordUtility.PASSWORD_MINIMUM_LENGTH;
import static en.ratings.own.my.utility.StringUtility.enumerateStrings;
import static en.ratings.own.my.utility.StringUtility.makeText;

public class UserExceptionText {

    public static String errorMessageForDefinedId() {
        return "The id must not be defined";
    }

    public static String errorMessageForEmailSyntax() {
        return "The specified email is not valid.";
    }

    public static String errorMessageForEmailTooLong() {
        return errorMessageStringAttributeIsTooLong("email", MAX_LENGTH_OF_EMAIL);
    }

    public static String errorMessageForEmailAlreadyExists() {
        return "The specified email already exists.";
    }

    public static String errorMessageForFirstNameTooLong() {
        return errorMessageStringAttributeIsTooLong("first name", MAX_LENGTH_OF_FIRST_NAME);
    }

    public static String errorMessageForSurnameTooLong() {
        return errorMessageStringAttributeIsTooLong("surname", MAX_LENGTH_OF_SURNAME);
    }

    public static String errorMessageForPasswordTooShort() {
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("The specified password is too short.");
        sentences.add("The password must have a minimum length of " + PASSWORD_MINIMUM_LENGTH + ".");

        return makeText(sentences);
    }

    public static String errorMessageForPasswordTooLong() {
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("The specified password is too long.");
        sentences.add("The password may have a maximum length of " + PASSWORD_MAXIMUM_LENGTH + ".");

        return makeText(sentences);
    }
    public static String errorMessageForPasswordHasNoDigit() {
        return "The password must have minimum one digit.";
    }

    public static String errorMessageForPasswordHasNoEnglishUpperCaseLetter() {
        return "The password must have minimum one English upper-case letter.";
    }

    public static String errorMessageForPasswordHasNoEnglishLowerCaseLetter() {
        return "The password must have minimum one English lower-case letter.";
    }

    public static String errorMessageForPasswordHasNoValidSpecialCharacter() {
        String text = "The password must have minimum one of the following special characters: ";
        text += enumerateStrings(LIST_OF_VALID_SPECIAL_CHARACTERS);
        text += ".";
        return text;
    }
}
