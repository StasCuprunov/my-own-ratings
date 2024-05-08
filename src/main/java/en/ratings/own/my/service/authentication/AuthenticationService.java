package en.ratings.own.my.service.authentication;

import en.ratings.own.my.dto.LoginDTO;
import en.ratings.own.my.exception.authentication.WrongPasswordLoginException;
import en.ratings.own.my.exception.user.UserByEmailNotFoundException;
import en.ratings.own.my.model.User;
import en.ratings.own.my.service.repository.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static en.ratings.own.my.constant.AttributeConstants.COOKIE_PATH;
import static en.ratings.own.my.constant.AttributeConstants.EXPIRATION_TIME_IN_MILLISECONDS;
import static en.ratings.own.my.constant.CookieConstants.AUTH_TOKEN;

@Service
public class AuthenticationService {

    private final UserRepositoryService userRepositoryService;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepositoryService userRepositoryService, JwtService jwtService,
                                 PasswordEncoder passwordEncoder) {
        this.userRepositoryService = userRepositoryService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseCookie login(LoginDTO loginDTO) throws Exception {
        String email = loginDTO.getEmail();
        User user = getUser(email);

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new WrongPasswordLoginException(email);
        }
        return createCookie(jwtService.generateToken(user.getEmail()));
    }

    private ResponseCookie createCookie(String token) {
        return ResponseCookie.from(AUTH_TOKEN, token).
                httpOnly(true).
                secure(true).
                path(COOKIE_PATH).
                maxAge(EXPIRATION_TIME_IN_MILLISECONDS).
                build();
    }

    private User getUser(String email) throws Exception {
        Optional<User> userResult = userRepositoryService.findByEmail(email);

        if (userResult.isEmpty()) {
            throw new UserByEmailNotFoundException(email);
        }
        return userResult.get();
    }
}
