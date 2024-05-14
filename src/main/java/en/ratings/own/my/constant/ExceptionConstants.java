package en.ratings.own.my.constant;

public final class ExceptionConstants {

    // For UserCreationFailedException
    public static final String KEY_EMAIL_SYNTAX = "emailSyntax";
    public static final String KEY_EMAIL_ALREADY_EXISTS = "emailAlreadyExists";
    public static final String KEY_PASSWORD_TOO_SHORT = "passwordTooShort";
    public static final String KEY_PASSWORD_TOO_LONG = "passwordTooLong";

    // For RatingCreationFailedException, RatingByIdNotFoundException, RatingUpdateFailedException
    public static final String KEY_USER_WITH_ID_NOT_FOUND = "userWithIdNotFound";
    public static final String KEY_MINIMUM_IS_TOO_BIG = "minimumIsTooBig";
    public static final String KEY_MINIMUM_IS_TOO_SMALL = "minimumIsTooSmall";
    public static final String KEY_MINIMUM_HAS_TOO_MANY_DECIMAL_DIGITS = "minimumHasTooManyDecimalDigits";
    public static final String KEY_MAXIMUM_IS_TOO_BIG = "maximumIsTooBig";
    public static final String KEY_MAXIMUM_HAS_TOO_MANY_DECIMAL_DIGITS = "maximumHasTooManyDecimalDigits";
    public static final String KEY_STEP_WIDTH_IS_TOO_SMALL = "stepWidthIsTooSmall";
    public static final String KEY_STEP_WIDTH_HAS_TOO_MANY_DECIMAL_DIGITS = "stepWidthHasTooManyDecimalDigits";
    public static final String KEY_RANGE_OF_VALUES_IS_NOT_CONSISTENT = "rangeOfValuesIsNotConsistent";
    public static final String KEY_RATING_NAME_ALREADY_USED_FOR_USER = "ratingNameAlreadyUsedForUser";
    public static final String KEY_RATING_BY_ID_NOT_FOUND = "ratingByIdNotFound";
    public static final String KEY_RATING_ENTRIES_DONT_FIT_IN_SCALE = "ratingEntriesDontFitInScale";

    // For RatingEntryFailedException
    public static final String KEY_RATING_ENTRY_BY_ID_NOT_FOUND = "ratingEntryByIdNotFound";
    public static final String KEY_RATING_ENTRY_NAME_ALREADY_USED_IN_RATING = "ratingEntryNameAlreadyUsedInRating";
    public static final String KEY_RATING_ENTRY_VALUE_IS_NOT_ALLOWED = "ratingEntryValueIsNotAllowed";
}
