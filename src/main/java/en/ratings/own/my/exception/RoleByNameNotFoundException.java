package en.ratings.own.my.exception;

public class RoleByNameNotFoundException extends Exception {
    public RoleByNameNotFoundException(String name) {
        super(errorMessage(name));
    }

    private static String errorMessage(String name) {
        return "Role with name " + name + " could not be found.";
    }
}
