package en.ratings.own.my.exception.text;

import java.util.ArrayList;

import static en.ratings.own.my.utility.PasswordUtility.PASSWORD_MAXIMUM_LENGTH;
import static en.ratings.own.my.utility.PasswordUtility.PASSWORD_MINIMUM_LENGTH;
import static en.ratings.own.my.utility.StringUtility.makeText;

public class UserExceptionText {

    public static String errorMessageForEmailSyntax() {
        return "The specified email is not valid.";
    }

    public static String errorMessageForEmailAlreadyExists() {
        return "The specified email already exists.";
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
}
