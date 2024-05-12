package en.ratings.own.my.test.integration.utility;

import en.ratings.own.my.model.User;

import static en.ratings.own.my.test.integration.constant.PasswordConstants.INVALID_PASSWORD_TOO_LONG;
import static en.ratings.own.my.test.integration.constant.PasswordConstants.INVALID_PASSWORD_TOO_SHORT;
import static en.ratings.own.my.test.integration.constant.PasswordConstants.INVALID_PASSWORD_WITHOUT_DIGITS;
import static en.ratings.own.my.test.integration.constant.PasswordConstants.
        INVALID_PASSWORD_WITHOUT_LOWER_CASE_LETTER;
import static en.ratings.own.my.test.integration.constant.PasswordConstants.
        INVALID_PASSWORD_WITHOUT_UPPER_CASE_LETTER;
import static en.ratings.own.my.test.integration.constant.PasswordConstants.
        INVALID_PASSWORD_WITHOUT_VALID_SPECIAL_CHARACTER;
import static en.ratings.own.my.test.integration.constant.PasswordConstants.
        INVALID_PASSWORD_WITH_INVALID_SPECIAL_CHARACTER;
import static en.ratings.own.my.test.integration.constant.PasswordConstants.VALID_PASSWORD_ONE;
import static en.ratings.own.my.test.integration.constant.PasswordConstants.VALID_PASSWORD_THREE;
import static en.ratings.own.my.test.integration.constant.PasswordConstants.VALID_PASSWORD_TWO;

public class CreateUserUtility {

    public static User createUserStevenWorm() {
        return new User("steven.worm@google.com", "Steven", "Worm", VALID_PASSWORD_ONE);
    }

    public static User createUserStevenWormWithDefinedId() {
        User user = createUserStevenWorm();
        user.setId("id");
        return user;
    }

    public static User createUserStevenWormWithDifferentFirstName() {
        User user = createUserStevenWorm();
        user.setFirstName("Manfred");
        return user;
    }

    public static User createUserStevenWormWithDifferentSurname() {
        User user = createUserStevenWorm();
        user.setSurname("Alfredo");
        return user;
    }

    public static User createUserStevenWormWithDifferentPassword() {
        User user = createUserStevenWorm();
        user.setPassword(VALID_PASSWORD_TWO);
        return user;
    }

    public static User createUserStevenWormWithInvalidEmail() {
        User user = createUserStevenWorm();
        user.setEmail("steven.worm-gmail.com");
        return user;
    }

    public static User createUserStevenWormWithTooShortPassword() {
        User user = createUserStevenWorm();
        user.setPassword(INVALID_PASSWORD_TOO_SHORT);
        return user;
    }

    public static User createUserStevenWormWithTooLongPassword() {
        User user = createUserStevenWorm();
        user.setPassword(INVALID_PASSWORD_TOO_LONG);
        return user;
    }

    public static User createUserStevenWormWithoutDigitsPassword() {
        User user = createUserStevenWorm();
        user.setPassword(INVALID_PASSWORD_WITHOUT_DIGITS);
        return user;
    }

    public static User createUserStevenWormWithoutLowerCaseLetterPassword() {
        User user = createUserStevenWorm();
        user.setPassword(INVALID_PASSWORD_WITHOUT_LOWER_CASE_LETTER);
        return user;
    }

    public static User createUserStevenWormWithoutUpperCaseLetterPassword() {
        User user = createUserStevenWorm();
        user.setPassword(INVALID_PASSWORD_WITHOUT_UPPER_CASE_LETTER);
        return user;
    }

    public static User createUserStevenWormWithoutValidSpecialCharacterPassword() {
        User user = createUserStevenWorm();
        user.setPassword(INVALID_PASSWORD_WITHOUT_VALID_SPECIAL_CHARACTER);
        return user;
    }

    public static User createUserStevenWormWithInvalidSpecialCharacterPassword() {
        User user = createUserStevenWorm();
        user.setPassword(INVALID_PASSWORD_WITH_INVALID_SPECIAL_CHARACTER);
        return user;
    }

    public static User createUserFalakNoorahKhoury() {
        return new User("falak.khoury@t-online.de", "Falak Noorah", "Khoury",
                VALID_PASSWORD_THREE);
    }
}
