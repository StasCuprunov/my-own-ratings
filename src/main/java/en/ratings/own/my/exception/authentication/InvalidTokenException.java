package en.ratings.own.my.exception.authentication;

public class InvalidTokenException extends Exception {
    public InvalidTokenException(String token) {
        super(errorMessage(token));
    }

    private static String errorMessage(String token) {
        return "The specified token is invalid: " + token;
    }
}
