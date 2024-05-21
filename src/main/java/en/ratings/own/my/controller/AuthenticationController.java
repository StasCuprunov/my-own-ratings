package en.ratings.own.my.controller;

import en.ratings.own.my.dto.LoginDTO;
import en.ratings.own.my.dto.RegistrationDTO;
import en.ratings.own.my.service.authentication.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static en.ratings.own.my.constant.RoutingConstants.ROUTING_LOGIN;
import static en.ratings.own.my.constant.RoutingConstants.ROUTING_REGISTRATION;
import static en.ratings.own.my.utility.ResponseEntityUtility.createOkResponseEntity;

@RestController
public class AuthenticationController {

    private final LoginService loginService;

    @Autowired
    public AuthenticationController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(ROUTING_LOGIN)
    public String login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) throws Exception {
        loginService.login(loginDTO, response);
        return "login";
    }

    @GetMapping(ROUTING_REGISTRATION)
    public ResponseEntity<RegistrationDTO> registration() {
        return createOkResponseEntity(new RegistrationDTO());
    }
}