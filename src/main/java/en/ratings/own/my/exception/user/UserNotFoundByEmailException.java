package en.ratings.own.my.exception.user;

public class UserNotFoundByEmailException extends Exception {
    public UserNotFoundByEmailException(String email) {
        super(errorMessage(email));
    }

    public static String errorMessage(String email) {
        return "User with email " + email + " could not be found.";
    }
}
