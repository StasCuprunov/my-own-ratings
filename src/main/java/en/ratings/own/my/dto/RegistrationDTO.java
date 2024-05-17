package en.ratings.own.my.dto;

import en.ratings.own.my.utility.PasswordUtility;

import java.util.ArrayList;

public class RegistrationDTO {
    private final int PASSWORD_MINIMUM_LENGTH = PasswordUtility.PASSWORD_MINIMUM_LENGTH;

    private final int PASSWORD_MAXIMUM_LENGTH = PasswordUtility.PASSWORD_MAXIMUM_LENGTH;

    private final String AT_LEAST_ONE_ENGLISH_UPPER_CASE_LETTER_REGEX =
            PasswordUtility.AT_LEAST_ONE_ENGLISH_UPPER_CASE_LETTER_REGEX;

    private final String AT_LEAST_ONE_ENGLISH_LOWER_CASE_LETTER_REGEX =
            PasswordUtility.AT_LEAST_ONE_ENGLISH_LOWER_CASE_LETTER_REGEX;

    private final String AT_LEAST_ONE_DIGIT_REGEX = PasswordUtility.AT_LEAST_ONE_DIGIT_REGEX;

    private final String AT_LEAST_ONE_VALID_SPECIAL_CHARACTER_REGEX =
            PasswordUtility.AT_LEAST_ONE_VALID_SPECIAL_CHARACTER_REGEX;

    private final ArrayList<String> LIST_OF_VALID_SPECIAL_CHARACTERS = PasswordUtility.LIST_OF_VALID_SPECIAL_CHARACTERS;
}
