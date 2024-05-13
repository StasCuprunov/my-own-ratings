package en.ratings.own.my.test.utility;

public class GeneratorUtility {
    public static final String ID_TEST = "test";

    public static String createNotExistentId(String existentId) {
        return existentId +  ID_TEST;
    }

    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
