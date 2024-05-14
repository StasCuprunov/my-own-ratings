package en.ratings.own.my.service.authentication;

import en.ratings.own.my.dto.LoginDTO;
import en.ratings.own.my.exception.authentication.WrongPasswordLoginException;
import en.ratings.own.my.model.User;
import en.ratings.own.my.service.repository.UserRepositoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static en.ratings.own.my.constant.AttributeConstants.EXPIRATION_TIME_IN_MILLISECONDS;
import static en.ratings.own.my.constant.CookieConstants.AUTH_TOKEN;
import static en.ratings.own.my.constant.CookieConstants.COOKIE_PATH;
import static org.springframework.http.HttpHeaders.SET_COOKIE;

@Service
public class LoginService {
    private final UserRepositoryService userRepositoryService;

    private final AuthenticationService authenticationService;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UserRepositoryService userRepositoryService, AuthenticationService authenticationService,
                        JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepositoryService = userRepositoryService;
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public void login(LoginDTO loginDTO, HttpServletResponse response) throws Exception {
        String email = loginDTO.getEmail();
        User user = getUser(email);

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new WrongPasswordLoginException(email);
        }
        authenticationService.setAuthentication(user);
        ResponseCookie cookie = createCookie(jwtService.generateToken(user.getEmail()));
        setAuthTokenCookie(cookie, response);
    }

    private ResponseCookie createCookie(String token) {
        return ResponseCookie.from(AUTH_TOKEN, token).
                httpOnly(true).
                secure(true).
                path(COOKIE_PATH).
                maxAge(EXPIRATION_TIME_IN_MILLISECONDS).
                build();
    }

    private void setAuthTokenCookie(ResponseCookie cookie, HttpServletResponse response) {
        response.addHeader(SET_COOKIE, cookie.toString());
    }

    private User getUser(String email) throws Exception {
        return userRepositoryService.findByEmail(email);
    }
}
