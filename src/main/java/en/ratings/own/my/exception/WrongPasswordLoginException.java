package en.ratings.own.my.exception;

public class WrongPasswordLoginException extends Exception {
    public WrongPasswordLoginException(String email) {
        super(errorMessage(email));
    }

    private static String errorMessage(String email) {
        return "The password for the email " + email + " is wrong.";
    }
}
