package en.ratings.own.my.utility;

import java.util.ArrayList;

import static en.ratings.own.my.utility.StringUtility.EMPTY_STRING;

public class PasswordUtility {

    public static final int PASSWORD_MINIMUM_LENGTH = 8;
    public static final int PASSWORD_MAXIMUM_LENGTH = 64;
    public static final String AT_LEAST_ONE_ENGLISH_UPPER_CASE_LETTER_REGEX = ".*[A-Z].*";
    public static final String AT_LEAST_ONE_ENGLISH_LOWER_CASE_LETTER_REGEX = ".*[a-z].*";
    public static final String AT_LEAST_ONE_DIGIT_REGEX = ".*[0-9].*";
    public static final String AT_LEAST_ONE_VALID_SPECIAL_CHARACTER_REGEX = ".*[#%&@^`~].*";

    public static final ArrayList<String> LIST_OF_VALID_SPECIAL_CHARACTERS = getValidSpecialCharacters();

    public static ArrayList<String> getValidSpecialCharacters() {
        String prefix = ".*[";
        String suffix = "].*";
        String specialCharacters = AT_LEAST_ONE_VALID_SPECIAL_CHARACTER_REGEX.replace(prefix, EMPTY_STRING).
                replace(suffix, EMPTY_STRING);
        int length = specialCharacters.length();
        ArrayList<String> list = new ArrayList<>();
        for (int index = 0; index < length; index++) {
            list.add(String.valueOf(specialCharacters.charAt(index)));
        }
        return list;
    }
}
