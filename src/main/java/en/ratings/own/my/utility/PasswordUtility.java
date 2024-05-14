package en.ratings.own.my.utility;

public class PasswordUtility {

    public static final int PASSWORD_MINIMUM_LENGTH = 6;
    public static final int PASSWORD_MAXIMUM_LENGTH = 64;

    public static final String AT_LEAST_ONE_ENGLISH_UPPER_CASE_LETTER_REGEX =
            createRegex("(?=.*?[A-Z])");

    public static final String AT_LEAST_ONE_ENGLISH_LOWER_CASE_LETTER_REGEX =
            createRegex("(?=.*?[a-z])");

    public static final String AT_LEAST_ONE_DIGIT_REGEX = createRegex("(?=.*?[0-9])");

    public static final String AT_LEAST_ONE_VALID_SPECIAL_CHARACTER_REGEX =
            createRegex(createAtLeastOneValidSpecialCharacterRegex());

    public static final String[] LIST_OF_VALID_SPECIAL_CHARACTERS = {"#", "$", "%", "&", "@", "^", "`", "~"};

    private static String createAtLeastOneValidSpecialCharacterRegex() {
        StringBuilder regex = new StringBuilder("(?=.*?[");
        for (String character: LIST_OF_VALID_SPECIAL_CHARACTERS) {
            regex.append(character);
        }
        regex.append("])");
        return regex.toString();
    }

    private static String createRegex(String regexWithoutBeginAndEnd) {
        return "^" + regexWithoutBeginAndEnd + "$";
    }
}
