package en.ratings.own.my.constant;

public final class ExceptionConstants {

    // For UserCreationFailedException
    public static final String KEY_EMAIL_SYNTAX = "emailSyntax";
    public static final String KEY_EMAIL_ALREADY_EXISTS = "emailAlreadyExists";
    public static final String KEY_PASSWORD_TOO_SHORT = "passwordTooShort";
    public static final String KEY_PASSWORD_TOO_LONG = "passwordTooLong";

    // For RatingCreationFailedException, RatingByIdNotFoundException, RatingUpdateFailedException
    public static final String KEY_USER_WITH_ID_NOT_FOUND = "userWithIdNotFound";
    public static final String KEY_MINIMUM_IS_NOT_VALID = "minimumNotValid";
    public static final String KEY_STEP_WIDTH_IS_TOO_SMALL = "stepWidthIsTooSmall";
    public static final String KEY_RANGE_OF_VALUES_IS_NOT_CONSISTENT = "rangeOfValuesIsNotConsistent";
    public static final String KEY_RATING_NAME_ALREADY_USED_FOR_USER = "ratingNameAlreadyUsedForUser";
    public static final String KEY_RATING_BY_ID_NOT_FOUND = "ratingByIdNotFound";
    public static final String KEY_RATING_ENTRIES_DONT_FIT_IN_SCALE = "ratingEntriesDontFitInScale";
}