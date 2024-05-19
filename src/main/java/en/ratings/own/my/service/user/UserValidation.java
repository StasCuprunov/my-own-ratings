package en.ratings.own.my.service.user;

import java.util.ArrayList;

import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_HAS_NO_DIGIT;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_HAS_NO_ENGLISH_LOWER_CASE_LETTER;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_HAS_NO_ENGLISH_UPPER_CASE_LETTER;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_HAS_NO_VALID_SPECIAL_CHARACTER;
import static en.ratings.own.my.utility.PasswordUtility.AT_LEAST_ONE_DIGIT_REGEX;
import static en.ratings.own.my.utility.PasswordUtility.AT_LEAST_ONE_ENGLISH_LOWER_CASE_LETTER_REGEX;
import static en.ratings.own.my.utility.PasswordUtility.AT_LEAST_ONE_ENGLISH_UPPER_CASE_LETTER_REGEX;
import static en.ratings.own.my.utility.PasswordUtility.AT_LEAST_ONE_VALID_SPECIAL_CHARACTER_REGEX;
import static en.ratings.own.my.utility.PasswordUtility.PASSWORD_MAXIMUM_LENGTH;
import static en.ratings.own.my.utility.PasswordUtility.PASSWORD_MINIMUM_LENGTH;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_TOO_LONG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_TOO_SHORT;
import static en.ratings.own.my.utility.StringUtility.addExistentStringToArrayList;

public class UserValidation {
    public static boolean isEmailSyntaxAllowed(String email) {
        return email.matches(regexEmailRFC5322());
    }

    public static ArrayList<String> passwordValidation(String password) {
        ArrayList<String> keysForException = new ArrayList<>();
        addExistentStringToArrayList(keysForException, passwordLengthValidation(password));
        addExistentStringToArrayList(keysForException, passwordHasAtLeastOneDigitValidation(password));
        addExistentStringToArrayList(keysForException, passwordHasAtLeastOneEnglishUpperCaseLetter(password));
        addExistentStringToArrayList(keysForException, passwordHasAtLeastOneEnglishLowerCaseLetter(password));
        addExistentStringToArrayList(keysForException, passwordHasAtLeastOneValidSpecialCharacter(password));
        return keysForException;
    }

    public static String regexEmailRFC5322() {
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

    private static String passwordHasAtLeastOneDigitValidation(String password) {
        if (password.matches(AT_LEAST_ONE_DIGIT_REGEX)) {
            return null;
        }
        return KEY_PASSWORD_HAS_NO_DIGIT;
    }

    private static String passwordHasAtLeastOneEnglishUpperCaseLetter(String password) {
        if (password.matches(AT_LEAST_ONE_ENGLISH_UPPER_CASE_LETTER_REGEX)) {
            return null;
        }
        return KEY_PASSWORD_HAS_NO_ENGLISH_UPPER_CASE_LETTER;
    }

    private static String passwordHasAtLeastOneEnglishLowerCaseLetter(String password) {
        if (password.matches(AT_LEAST_ONE_ENGLISH_LOWER_CASE_LETTER_REGEX)) {
            return null;
        }
        return KEY_PASSWORD_HAS_NO_ENGLISH_LOWER_CASE_LETTER;
    }

    private static String passwordHasAtLeastOneValidSpecialCharacter(String password) {
        if (password.matches(AT_LEAST_ONE_VALID_SPECIAL_CHARACTER_REGEX)) {
            return null;
        }
        return KEY_PASSWORD_HAS_NO_VALID_SPECIAL_CHARACTER;
    }
}
