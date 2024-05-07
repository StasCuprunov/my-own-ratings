package en.ratings.own.my.constant;

import en.ratings.own.my.enums.RoleEnum;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashMap;

import static en.ratings.own.my.utility.EnumUtility.roleAdminAsString;
import static en.ratings.own.my.utility.EnumUtility.roleUserAsString;
import static en.ratings.own.my.utility.PermissionUtility.createSimpleGrantedAuthority;

public class PermissionConstants {
    public static final String ROLE_PREFIX = "ROLE_";
    public static final HashMap<String, SimpleGrantedAuthority> LIST_OF_GRANTED_AUTHORITIES =
            createGrantedAuthorities();
    public static final SimpleGrantedAuthority USER_AUTHORITY = LIST_OF_GRANTED_AUTHORITIES.get(roleUserAsString());
    public static final SimpleGrantedAuthority ADMIN_AUTHORITY = LIST_OF_GRANTED_AUTHORITIES.get(roleAdminAsString());
    public static final String HAS_ROLE_USER_PERMISSION = "hasRole('ROLE_USER')";
    public static final String HAS_ROLE_ADMIN_PERMISSION = "hasRole('ROLE_ADMIN')";
    public static final String IS_AUTHENTICATED_PERMISSION = "isAuthenticated()";

    private static HashMap<String, SimpleGrantedAuthority> createGrantedAuthorities() {
        HashMap<String, SimpleGrantedAuthority> authorities = new HashMap<>();
        for (RoleEnum role: RoleEnum.values()) {
            String roleName = role.name();
            authorities.put(roleName, createSimpleGrantedAuthority(roleName));
        }
        return authorities;
    }
}
