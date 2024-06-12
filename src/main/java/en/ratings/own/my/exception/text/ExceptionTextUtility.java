package en.ratings.own.my.exception.text;

public class ExceptionTextUtility {

    public static String errorMessageStringAttributeIsTooLong(String attributeName, int maximumLength) {
        return "The specified " + attributeName + " may have maximum " + maximumLength + " characters.";
    }
}
