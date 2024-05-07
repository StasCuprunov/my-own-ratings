package en.ratings.own.my.utility;

import static en.ratings.own.my.enums.RoleEnum.USER;
import static en.ratings.own.my.enums.RoleEnum.ADMIN;

public class EnumUtility {

    public static String roleUserAsString() {
        return USER.name();
    }
    public static String roleAdminAsString() { return ADMIN.name(); }
}
