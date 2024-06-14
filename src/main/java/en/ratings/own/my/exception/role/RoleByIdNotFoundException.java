package en.ratings.own.my.exception.role;

public class RoleByIdNotFoundException extends Exception {
    public RoleByIdNotFoundException(String id) {
        super(errorMessage(id));
    }

    private static String errorMessage(String id) {
        return "Role with id " + id + " could not be found.";
    }
}
