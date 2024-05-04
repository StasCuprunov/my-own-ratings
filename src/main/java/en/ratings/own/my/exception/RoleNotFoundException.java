package en.ratings.own.my.exception;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException(String name) {
        super(errorMessage(name));
    }

    private static String errorMessage(String name) {
        return "Role with name " + name + " could not be found.";
    }
}
