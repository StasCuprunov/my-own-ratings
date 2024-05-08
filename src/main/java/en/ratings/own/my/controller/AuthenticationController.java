package en.ratings.own.my.controller;

import en.ratings.own.my.dto.LoginDTO;
import en.ratings.own.my.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static en.ratings.own.my.constant.PermissionConstants.ADMIN_AUTHORITY;
import static en.ratings.own.my.constant.PermissionConstants.IS_AUTHENTICATED_PERMISSION;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_LOGIN;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_LOGOUT;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(ROUTING_LOGIN)
    public String login(@RequestBody LoginDTO loginDTO) throws Exception {
        String token = authenticationService.login(loginDTO);
        Authentication authentication = getAuthentication();

        if (containsSpecificAuthority(authentication, ADMIN_AUTHORITY)) {
            return "admin";
        }
        return token;
    }

    @PreAuthorize(IS_AUTHENTICATED_PERMISSION)
    @PostMapping(ROUTING_LOGOUT)
    public String logout() {
        return "logout";
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private boolean containsSpecificAuthority(Authentication authentication,
                                              SimpleGrantedAuthority simpleGrantedAuthority) {
        return authentication.getAuthorities().contains(simpleGrantedAuthority);
    }
}