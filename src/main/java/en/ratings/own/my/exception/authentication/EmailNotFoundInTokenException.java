package en.ratings.own.my.exception.authentication;

public class EmailNotFoundInTokenException extends Exception{
    public EmailNotFoundInTokenException(String token) {
        super(errorMessage(token));
    }

    private static String errorMessage(String token) {
        return "Email could not be found in token " + token;
    }
}
