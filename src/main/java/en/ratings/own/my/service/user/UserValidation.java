package en.ratings.own.my.service.user;

import java.util.ArrayList;

import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_HAS_NO_DIGIT;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_HAS_NO_ENGLISH_LOWER_CASE_LETTER;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_HAS_NO_ENGLISH_UPPER_CASE_LETTER;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_HAS_NO_VALID_SPECIAL_CHARACTER;
import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_EMAIL;
import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_FIRST_NAME;
import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_SURNAME;
import static en.ratings.own.my.utility.PasswordUtility.AT_LEAST_ONE_DIGIT_REGEX;
import static en.ratings.own.my.utility.PasswordUtility.AT_LEAST_ONE_ENGLISH_LOWER_CASE_LETTER_REGEX;
import static en.ratings.own.my.utility.PasswordUtility.AT_LEAST_ONE_ENGLISH_UPPER_CASE_LETTER_REGEX;
import static en.ratings.own.my.utility.PasswordUtility.AT_LEAST_ONE_VALID_SPECIAL_CHARACTER_REGEX;
import static en.ratings.own.my.utility.PasswordUtility.PASSWORD_MAXIMUM_LENGTH;
import static en.ratings.own.my.utility.PasswordUtility.PASSWORD_MINIMUM_LENGTH;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_TOO_LONG;
import static en.ratings.own.my.constant.ExceptionConstants.KEY_PASSWORD_TOO_SHORT;
import static en.ratings.own.my.utility.StringUtility.addExistentStringToArrayList;
import static en.ratings.own.my.utility.StringUtility.isStringTooLong;

public class UserValidation {
    public static boolean isEmailSyntaxAllowed(String email) {
        return email.matches(regexEmailRFC5322());
    }

    public static boolean isEmailTooLong(String email) {
        return isStringTooLong(email, MAX_LENGTH_OF_EMAIL);
    }

    public static boolean isFirstNameTooLong(String firstName) {
        return isStringTooLong(firstName, MAX_LENGTH_OF_FIRST_NAME);
    }

    public static boolean isSurnameTooLong(String surname) {
        return isStringTooLong(surname, MAX_LENGTH_OF_SURNAME);
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
        return "[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+";
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
