package en.ratings.own.my.utility;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static en.ratings.own.my.constant.PermissionConstants.ROLE_PREFIX;

public class PermissionUtility {

    public static SimpleGrantedAuthority createSimpleGrantedAuthority(String name) {
        return new SimpleGrantedAuthority(ROLE_PREFIX + name);
    }
}
