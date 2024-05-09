package en.ratings.own.my.component;

import en.ratings.own.my.exception.authentication.EmailNotFoundInTokenException;
import en.ratings.own.my.exception.authentication.InvalidTokenException;
import en.ratings.own.my.model.User;
import en.ratings.own.my.service.authentication.AuthenticationService;
import en.ratings.own.my.service.authentication.JwtService;
import en.ratings.own.my.service.repository.UserRepositoryService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static en.ratings.own.my.constant.CookieConstants.AUTH_TOKEN;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private final UserRepositoryService userRepositoryService;

    @Autowired
    public JwtAuthFilter(JwtService jwtService, AuthenticationService authenticationService,
                         UserRepositoryService userRepositoryService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userRepositoryService = userRepositoryService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getAuthToken(request);
        if (token != null) {
            try {
                authentication(token, request);
            } catch (Exception ignored) {

            }
        }
        filterChain.doFilter(request, response);
    }

    private String getAuthToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(AUTH_TOKEN)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private void authentication(String token, HttpServletRequest request) throws Exception {
        String email = jwtService.extractEmail(token);
        if (email == null) {
            throw new EmailNotFoundInTokenException(token);
        }
        User user = userRepositoryService.findByEmail(email);

        if (!jwtService.validateToken(token, email)) {
            throw new InvalidTokenException(token);
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                authenticationService.createAuthenticationToken(user);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        authenticationService.setAuthentication(authenticationToken);
    }

}
