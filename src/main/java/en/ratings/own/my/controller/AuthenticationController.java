package en.ratings.own.my.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static en.ratings.own.my.constant.RoutingConstants.ROUTING_LOGIN;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_LOGOUT;

@RestController
public class AuthenticationController {
    @PostMapping(ROUTING_LOGIN)
    public String login() {
        return "login";
    }

    @PostMapping(ROUTING_LOGOUT)
    public String logout() {
        return "logout";
    }
}