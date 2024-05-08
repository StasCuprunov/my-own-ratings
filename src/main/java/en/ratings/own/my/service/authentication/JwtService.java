package en.ratings.own.my.service.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.SIG.HS512;
import static io.jsonwebtoken.io.Decoders.BASE64;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.lang.System.currentTimeMillis;

@Service
public class JwtService {
    private final String SECRET_KEY_VALUE =
            "b7ynPg1T65tpbz7WbTi9g5hu75FBfu2pDR8xe5NiLRYQJ/JLLVlJ6TuqllIDZyaDsSaxv045vwtpaNnB+l+sXA";

    private final long EXPIRATION_TIME_IN_MILLISECONDS = 86400000; // 1 day

    private final String TOKEN_TYPE_KEY = "typ";

    private final String TOKEN_TYPE_VALUE = "jwt";

    private final SecretKey SECRET_KEY = hmacShaKeyFor(convertSecretKeyToBase64());

    public String generateToken(String email) {
        return createToken(new HashMap<>(), email);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpirationTime(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean validateToken(String token, String email) {
        return !isTokenExpired(token) && extractEmail(token).equals(email);
    }

    private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder().
                header().add(TOKEN_TYPE_KEY, TOKEN_TYPE_VALUE).
                and().
                subject(email).claims(claims).issuedAt(issuedDate()).expiration(expirationDate()).
                signWith(SECRET_KEY, HS512).
                compact();
    }

    private Date expirationDate() {
        return new Date(currentTimeMillis() + EXPIRATION_TIME_IN_MILLISECONDS);
    }

    private Date issuedDate() {
        return new Date(currentTimeMillis());
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationTime(token).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.
                parser().
                verifyWith(SECRET_KEY).
                build().
                parseSignedClaims(token).
                getPayload();
    }

    private byte[] convertSecretKeyToBase64() {
        return BASE64.decode(SECRET_KEY_VALUE);
    }
}