package en.ratings.own.my.exception.user;

public class UserByEmailNotFoundException extends Exception {
    public UserByEmailNotFoundException(String email) {
        super(errorMessage(email));
    }

    public static String errorMessage(String email) {
        return "User with email " + email + " could not be found.";
    }
}
