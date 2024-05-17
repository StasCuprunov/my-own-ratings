package en.ratings.own.my.dto;

import en.ratings.own.my.utility.PasswordUtility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistrationDTO {
    private int passwordMinimumLength = PasswordUtility.PASSWORD_MINIMUM_LENGTH;

    private int passwordMaximumLength = PasswordUtility.PASSWORD_MAXIMUM_LENGTH;

    private String atLeastOneEnglishUpperCaseLetterRegex =
            PasswordUtility.AT_LEAST_ONE_ENGLISH_UPPER_CASE_LETTER_REGEX;

    private String atLeastOneEnglishLowerCaseLetterRegex =
            PasswordUtility.AT_LEAST_ONE_ENGLISH_LOWER_CASE_LETTER_REGEX;

    private String atLeastOneDigitRegex = PasswordUtility.AT_LEAST_ONE_DIGIT_REGEX;

    private String atLeastOneValidSpecialCharacterRegex =
            PasswordUtility.AT_LEAST_ONE_VALID_SPECIAL_CHARACTER_REGEX;

    private ArrayList<String> listOfValidSpecialCharacters = PasswordUtility.LIST_OF_VALID_SPECIAL_CHARACTERS;
}
