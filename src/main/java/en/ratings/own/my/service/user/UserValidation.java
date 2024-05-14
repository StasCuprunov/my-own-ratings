package en.ratings.own.my.service.user;

import static en.ratings.own.my.utility.PasswordUtility.PASSWORD_MAXIMUM_LENGTH;
import static en.ratings.own.my.utility.PasswordUtility.PASSWORD_MINIMUM_LENGTH;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_TOO_LONG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_TOO_SHORT;

public class UserValidation {
    public static boolean isEmailSyntaxAllowed(String email) {
        return email.matches(regexEmailRFC5322());
    }

    public static String passwordValidation(String password) {
        return passwordLengthValidation(password);
    }

    private static String regexEmailRFC5322() {
        return "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    }

    private static String passwordLengthValidation(String password) {
        int passwordLength = password.length();

        if (passwordLength < PASSWORD_MINIMUM_LENGTH) {
            return KEY_PASSWORD_TOO_SHORT;
        }
        else if (passwordLength > PASSWORD_MAXIMUM_LENGTH) {
            return KEY_PASSWORD_TOO_LONG;
        }
        return null;
    }

}
