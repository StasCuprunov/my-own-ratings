package en.ratings.own.my.controller;

import en.ratings.own.my.dto.LoginDTO;
import en.ratings.own.my.service.authentication.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static en.ratings.own.my.constant.PermissionConstants.IS_AUTHENTICATED_PERMISSION;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_LOGIN;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_LOGOUT;
import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(ROUTING_LOGIN)
    public String login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) throws Exception {
        response.addHeader(SET_COOKIE, authenticationService.login(loginDTO).toString());
        return "login";
    }

    @PreAuthorize(IS_AUTHENTICATED_PERMISSION)
    @PostMapping(ROUTING_LOGOUT)
    public String logout() {
        return "logout";
    }
}