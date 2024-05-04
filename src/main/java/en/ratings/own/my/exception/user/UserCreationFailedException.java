package en.ratings.own.my.exception.user;

import java.util.ArrayList;

import static en.ratings.own.my.constant.AttributeConstants.PASSWORD_MAXIMUM_LENGTH;
import static en.ratings.own.my.constant.AttributeConstants.PASSWORD_MINIMUM_LENGTH;

import static en.ratings.own.my.constant.ExceptionConstants.KEY_EMAIL_SYNTAX;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_EMAIL_ALREADY_EXISTS;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_TOO_SHORT;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_TOO_LONG;

import static en.ratings.own.my.utility.StringUtility.makeText;

public class UserCreationFailedException extends Exception {
    public UserCreationFailedException(ArrayList<String> keysForException) {
        super(errorMessage(keysForException));
    }

    private static String errorMessage(ArrayList<String> keysForException) {
        ArrayList<String> errorMessage = new ArrayList<>();

        for (String key : keysForException) {
            switch (key) {
                case KEY_EMAIL_SYNTAX -> errorMessage.add(errorMessageForEmailSyntax());
                case KEY_EMAIL_ALREADY_EXISTS -> errorMessage.add(errorMessageForEmailAlreadyExists());
                case KEY_PASSWORD_TOO_SHORT -> errorMessage.add(errorMessageForPasswordTooShort());
                case KEY_PASSWORD_TOO_LONG -> errorMessage.add(errorMessageForPasswordTooLong());
            }
        }
        return makeText(errorMessage);
    }

    private static String errorMessageForEmailSyntax() {
        return "The specified email is not valid.";
    }

    private static String errorMessageForEmailAlreadyExists() {
        return "The specified email already exists.";
    }

    private static String errorMessageForPasswordTooShort() {
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("The specified password is too short.");
        sentences.add("The password must have a minimum length of " + PASSWORD_MINIMUM_LENGTH + ".");

        return makeText(sentences);
    }

    private static String errorMessageForPasswordTooLong() {
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("The specified password is too long.");
        sentences.add("The password may have a maximum length of " + PASSWORD_MAXIMUM_LENGTH + ".");

        return makeText(sentences);
    }

}
