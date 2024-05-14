package en.ratings.own.my.utility;

public class PasswordUtility {

    public static final int PASSWORD_MINIMUM_LENGTH = 6;
    public static final int PASSWORD_MAXIMUM_LENGTH = 64;
    public static final String PASSWORD_REGEX = createPasswordRegex();
    private static final String LENGTH_REGEX = "{" + PASSWORD_MINIMUM_LENGTH + "," + PASSWORD_MAXIMUM_LENGTH + "}";

    private static final String AT_LEAST_ONE_ENGLISH_UPPER_CASE_LETTER_REGEX = "(?=.*?[A-Z])";

    private static final String AT_LEAST_ONE_ENGLISH_LOWER_CASE_LETTER_REGEX = "(?=.*?[a-z])";

    private static final String AT_LEAST_ONE_DIGIT_REGEX = "(?=.*?[0-9])";

    private static final String AT_LEAST_ONE_VALID_SPECIAL_CHARACTER = createAtLeastOneValidSpecialCharacterRegex();

    private static final String[] LIST_OF_VALID_SPECIAL_CHARACTERS = {"#", "$", "%", "&", "@", "^", "`", "~"};

    private static String createAtLeastOneValidSpecialCharacterRegex() {
        StringBuilder regex = new StringBuilder("(?=.*?[");
        for (String character: LIST_OF_VALID_SPECIAL_CHARACTERS) {
            regex.append(character);
        }
        regex.append("])");
        return regex.toString();
    }

    private static String createPasswordRegex() {
        String regex = "^";
        regex += AT_LEAST_ONE_ENGLISH_UPPER_CASE_LETTER_REGEX + AT_LEAST_ONE_ENGLISH_LOWER_CASE_LETTER_REGEX +
                AT_LEAST_ONE_DIGIT_REGEX + AT_LEAST_ONE_VALID_SPECIAL_CHARACTER;
        regex += "." + LENGTH_REGEX + "$";
        return regex;
    }
}
