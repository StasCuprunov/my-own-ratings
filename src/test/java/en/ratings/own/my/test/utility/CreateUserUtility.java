package en.ratings.own.my.test.utility;

import en.ratings.own.my.model.User;

import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_EMAIL;
import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_FIRST_NAME;
import static en.ratings.own.my.constant.MaxLengthConstants.MAX_LENGTH_OF_SURNAME;
import static en.ratings.own.my.test.constant.PasswordConstants.INVALID_PASSWORD_TOO_LONG;
import static en.ratings.own.my.test.constant.PasswordConstants.INVALID_PASSWORD_TOO_SHORT;
import static en.ratings.own.my.test.constant.PasswordConstants.INVALID_PASSWORD_WITHOUT_DIGITS;
import static en.ratings.own.my.test.constant.PasswordConstants.
        INVALID_PASSWORD_WITHOUT_LOWER_CASE_LETTER;
import static en.ratings.own.my.test.constant.PasswordConstants.
        INVALID_PASSWORD_WITHOUT_UPPER_CASE_LETTER;
import static en.ratings.own.my.test.constant.PasswordConstants.
        INVALID_PASSWORD_WITHOUT_VALID_SPECIAL_CHARACTER;
import static en.ratings.own.my.test.constant.PasswordConstants.
        INVALID_PASSWORD_WITH_INVALID_SPECIAL_CHARACTER;
import static en.ratings.own.my.test.constant.PasswordConstants.VALID_PASSWORD_ONE;
import static en.ratings.own.my.test.constant.PasswordConstants.VALID_PASSWORD_THREE;
import static en.ratings.own.my.test.constant.PasswordConstants.VALID_PASSWORD_TWO;
import static en.ratings.own.my.test.utility.GeneratorUtility.ID_TEST;
import static en.ratings.own.my.test.utility.GeneratorUtility.generateTooLongString;

public class CreateUserUtility {

    public static User createUserStevenWorm() {
        return new User("steven.worm@google.com", "Steven", "Worm", VALID_PASSWORD_ONE);
    }
    public static User createUserFalakNoorahKhoury() {
        return new User("falak.khoury@t-online.de", "Falak Noorah", "Khoury",
                VALID_PASSWORD_THREE);
    }

    public static User createUserLiangPai() {
        return new User("liang.pai@gmx.de", "Liang", "Pai", VALID_PASSWORD_TWO);
    }

    public static User createUserLiangPaiWithDefinedId() {
        User user = createUserLiangPai();
        user.setId(ID_TEST);
        return user;
    }

    public static User createUserLiangPaiWithDifferentFirstName() {
        User user = createUserLiangPai();
        user.setFirstName("Manfred");
        return user;
    }

    public static User createUserLiangPaiWithDifferentSurname() {
        User user = createUserLiangPai();
        user.setSurname("Alfredo");
        return user;
    }

    public static User createUserLiangPaiWithDifferentPassword() {
        User user = createUserLiangPai();
        user.setPassword(VALID_PASSWORD_THREE);
        return user;
    }

    public static User createUserLiangPaiWithInvalidEmail() {
        User user = createUserLiangPai();
        user.setEmail("liang.pai-gmail.com");
        return user;
    }

    public static User createUserLiangPaiWithTooLongEmail() {
        User user = createUserLiangPai();
        String email = user.getEmail();
        user.setEmail(generateTooLongString(MAX_LENGTH_OF_EMAIL, email));

        return user;
    }

    public static User createUserLianPaiWithTooLongFirstName() {
        User user = createUserLiangPai();
        String firstName = user.getFirstName();
        user.setFirstName(generateTooLongString(MAX_LENGTH_OF_FIRST_NAME, firstName));

        return user;
    }

    public static User createUserLianPaiWithTooLongSurname() {
        User user = createUserLiangPai();
        String surname = user.getSurname();
        user.setSurname(generateTooLongString(MAX_LENGTH_OF_SURNAME, surname));

        return user;
    }

    public static User createUserLiangPaiWithTooShortPassword() {
        User user = createUserLiangPai();
        user.setPassword(INVALID_PASSWORD_TOO_SHORT);
        return user;
    }

    public static User createUserLiangPaiWithTooLongPassword() {
        User user = createUserLiangPai();
        user.setPassword(INVALID_PASSWORD_TOO_LONG);
        return user;
    }

    public static User createUserLiangPaiWithoutDigitsPassword() {
        User user = createUserLiangPai();
        user.setPassword(INVALID_PASSWORD_WITHOUT_DIGITS);
        return user;
    }

    public static User createUserLiangPaiWithoutLowerCaseLetterPassword() {
        User user = createUserLiangPai();
        user.setPassword(INVALID_PASSWORD_WITHOUT_LOWER_CASE_LETTER);
        return user;
    }

    public static User createUserLiangPaiWithoutUpperCaseLetterPassword() {
        User user = createUserLiangPai();
        user.setPassword(INVALID_PASSWORD_WITHOUT_UPPER_CASE_LETTER);
        return user;
    }

    public static User createUserLiangPaiWithoutValidSpecialCharacterPassword() {
        User user = createUserLiangPai();
        user.setPassword(INVALID_PASSWORD_WITHOUT_VALID_SPECIAL_CHARACTER);
        return user;
    }

    public static User createUserLiangPaiWithInvalidSpecialCharacterPassword() {
        User user = createUserLiangPai();
        user.setPassword(INVALID_PASSWORD_WITH_INVALID_SPECIAL_CHARACTER);
        return user;
    }
}
