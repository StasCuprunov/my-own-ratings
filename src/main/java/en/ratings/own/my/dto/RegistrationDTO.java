package en.ratings.own.my.dto;

import en.ratings.own.my.utility.PasswordUtility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static en.ratings.own.my.constant.AttributeConstants.MAXIMUM_LENGTH_OF_SMALL_STRING;
import static en.ratings.own.my.service.user.UserValidation.regexEmailRFC5322;
import static en.ratings.own.my.utility.StringUtility.enumerateStrings;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistrationDTO {

    private int maximumLengthOfString = MAXIMUM_LENGTH_OF_SMALL_STRING;

    private String emailRegex = regexEmailRFC5322();

    private int passwordMinimumLength = PasswordUtility.PASSWORD_MINIMUM_LENGTH;

    private int passwordMaximumLength = PasswordUtility.PASSWORD_MAXIMUM_LENGTH;

    private String atLeastOneEnglishUpperCaseLetterRegex =
            PasswordUtility.AT_LEAST_ONE_ENGLISH_UPPER_CASE_LETTER_REGEX;

    private String atLeastOneEnglishLowerCaseLetterRegex =
            PasswordUtility.AT_LEAST_ONE_ENGLISH_LOWER_CASE_LETTER_REGEX;

    private String atLeastOneDigitRegex = PasswordUtility.AT_LEAST_ONE_DIGIT_REGEX;

    private String atLeastOneValidSpecialCharacterRegex =
            PasswordUtility.AT_LEAST_ONE_VALID_SPECIAL_CHARACTER_REGEX;

    private String enumerationOfValidSpecialCharacters =
            enumerateStrings(PasswordUtility.LIST_OF_VALID_SPECIAL_CHARACTERS);
}
