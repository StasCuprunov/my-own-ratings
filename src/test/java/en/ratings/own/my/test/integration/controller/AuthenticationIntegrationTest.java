package en.ratings.own.my.test.integration.controller;

import en.ratings.own.my.AbstractIntegrationTest;
import en.ratings.own.my.controller.AuthenticationController;
import en.ratings.own.my.controller.UserController;
import en.ratings.own.my.dto.LoginDTO;
import en.ratings.own.my.model.User;
import en.ratings.own.my.service.authentication.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static en.ratings.own.my.AssertThatUtility.assertThatExceptionIsEqualToUserByEmailNotFoundException;
import static en.ratings.own.my.AssertThatUtility.assertThatExceptionIsEqualToWrongPasswordLoginException;
import static en.ratings.own.my.constant.AttributeConstants.EXPIRATION_TIME_IN_MILLISECONDS;
import static en.ratings.own.my.constant.CookieConstants.AUTH_TOKEN;
import static en.ratings.own.my.constant.CookieConstants.HTTP_ONLY;
import static en.ratings.own.my.constant.CookieConstants.MAX_AGE;
import static en.ratings.own.my.constant.CookieConstants.SAME_SITE;
import static en.ratings.own.my.constant.CookieConstants.SAME_SITE_VALUE;
import static en.ratings.own.my.constant.CookieConstants.SECURE;
import static en.ratings.own.my.test.integration.controller.utility.CreateUserUtility.createUserFalakNoorahKhoury;
import static en.ratings.own.my.test.integration.controller.utility.CreateUserUtility.createUserStevenWorm;
import static en.ratings.own.my.test.integration.controller.utility.HttpResponseUtility.createHttpServletResponse;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.SET_COOKIE;

public class AuthenticationIntegrationTest extends AbstractIntegrationTest {
    private static final String COOKIE_ASSIGNMENT = "=";
    private static final String COOKIE_SEPARATOR = ";";
    private static final String SPACE_CHARACTER = " ";
    private static final String EMPTY_STRING = "";
    private static final int COOKIE_KEY_VALUE_NUMBER = 2;
    private static final int INDEX_OF_VALUE_FROM_KEY_VALUE_PAIR = 1;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private UserController userController;

    @Autowired
    private JwtService jwtService;

    @Test
    public void testLoginWithValidAccount() {
        LoginDTO loginDTO = createUserForLogin();
        HttpServletResponse response = createHttpServletResponse();

        try {
            authenticationController.login(loginDTO, response);
        } catch (Exception ignored) {

        }
        assertThat(response.getStatus()).isEqualTo(SC_OK);
        testCookiesAfterSuccessfulLogin(loginDTO.getEmail(), response);
    }

    @Test
    public void testLoginWithFalseEmail() {
        LoginDTO loginDTO = createUserForLogin();
        loginDTO.setEmail(createUserStevenWorm().getEmail());
        testLoginWithFalseEmail(loginDTO);
    }

    @Test
    public void testLoginWithFalsePassword() {
        LoginDTO loginDTO = createUserForLogin();
        loginDTO.setPassword(createUserStevenWorm().getPassword());
        testLoginWithFalsePassword(loginDTO);
    }

    private void testLoginWithFalseEmail(LoginDTO loginDTO) {
        HttpServletResponse response = createHttpServletResponse();
        Exception foundException = new Exception();
        try {
            authenticationController.login(loginDTO, response);
        } catch (Exception e) {
            foundException = e;
        }
        assertThatExceptionIsEqualToUserByEmailNotFoundException(foundException);
    }

    private void testLoginWithFalsePassword(LoginDTO loginDTO) {
        HttpServletResponse response = createHttpServletResponse();
        Exception foundException = new Exception();
        try {
            authenticationController.login(loginDTO, response);
        } catch (Exception e) {
            foundException = e;
        }
        assertThatExceptionIsEqualToWrongPasswordLoginException(foundException);
    }

    private LoginDTO createUserForLogin() {
        User user = createUserFalakNoorahKhoury();
        String rawPassword = user.getPassword();
        try {
            userController.create(user);
        } catch (Exception ignored) {

        }
        return new LoginDTO(user.getEmail(), rawPassword);
    }

    private void testCookiesAfterSuccessfulLogin(String email, HttpServletResponse response) {
        String cookies = response.getHeader(SET_COOKIE);
        String[] cookieList = cookies.split(COOKIE_SEPARATOR);
        boolean hasCorrectAuthToken = false;
        boolean hasSecure = false;
        boolean hasHttpOnly = false;
        boolean hasCorrectSameSite = false;
        boolean hasCorrectMaxAge = false;
        for (String cookie: cookieList) {
            if (cookie.contains(keyWithAssignment(AUTH_TOKEN))) {
                hasCorrectAuthToken = hasCorrectAuthToken(email, cookie);
            }
            else if (exactMatchInCookie(SECURE, cookie)) {
                hasSecure = true;
            }
            else if (exactMatchInCookie(HTTP_ONLY, cookie)) {
                hasHttpOnly = true;
            }
            else if (cookie.contains(keyWithAssignment(MAX_AGE))) {
                hasCorrectMaxAge = hasCorrectMaxAge(cookie);
            }
            else if (cookie.contains(keyWithAssignment(SAME_SITE))) {
                hasCorrectSameSite = hasCorrectSameSite(cookie);
            }
        }
        assertThat(hasCorrectAuthToken).isTrue();
        assertThat(hasSecure).isTrue();
        assertThat(hasHttpOnly).isTrue();
        assertThat(hasCorrectMaxAge).isTrue();
        assertThat(hasCorrectSameSite).isTrue();
    }

    private boolean exactMatchInCookie(String key, String cookie) {
        return cookie.replace(SPACE_CHARACTER, EMPTY_STRING).equals(key);
    }

    private String keyWithAssignment(String key) {
        return key + COOKIE_ASSIGNMENT;
    }

    private boolean hasCorrectAuthToken(String email, String cookie) {
        String[] authTokenPair = cookie.split(COOKIE_ASSIGNMENT);
        if (authTokenPair.length != COOKIE_KEY_VALUE_NUMBER) {
            return false;
        }
        String token = authTokenPair[INDEX_OF_VALUE_FROM_KEY_VALUE_PAIR];
        return jwtService.validateToken(token, email);
    }

    private boolean hasCorrectSameSite(String cookie) {
        String[] sameSitePair = cookie.split(COOKIE_ASSIGNMENT);
        if (sameSitePair.length != COOKIE_KEY_VALUE_NUMBER) {
            return false;
        }
        return sameSitePair[INDEX_OF_VALUE_FROM_KEY_VALUE_PAIR].equals(SAME_SITE_VALUE);
    }

    private boolean hasCorrectMaxAge(String cookie) {
        String[] maxAgePair = cookie.split(COOKIE_ASSIGNMENT);
        if (maxAgePair.length != COOKIE_KEY_VALUE_NUMBER) {
            return false;
        }
        return maxAgePair[INDEX_OF_VALUE_FROM_KEY_VALUE_PAIR].equals(String.valueOf(EXPIRATION_TIME_IN_MILLISECONDS));
    }
}
