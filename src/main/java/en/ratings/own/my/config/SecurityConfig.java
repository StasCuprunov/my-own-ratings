package en.ratings.own.my.config;

import en.ratings.own.my.component.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static en.ratings.own.my.constant.CORSConstants.CORS_ALLOWED_METHODS;
import static en.ratings.own.my.constant.CORSConstants.CORS_PATTERN;
import static en.ratings.own.my.constant.CookieConstants.AUTH_TOKEN;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private static final int PASSWORD_STRENGTH = 10;

    private final JwtAuthFilter jwtAuthFilter;

    @Value("${frontend.url}")
    private String frontendUrl;

    @Autowired
    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.
                httpBasic(Customizer.withDefaults()).
                logout(configurer -> configurer.
                        clearAuthentication(true).
                        deleteCookies(AUTH_TOKEN)
                ).
                csrf(AbstractHttpConfigurer::disable).
                cors(configurer -> configurer.configurationSource(corsConfigurationSource())).
                sessionManagement(configurer -> configurer.sessionCreationPolicy(STATELESS)).
                addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).
                build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(PASSWORD_STRENGTH);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(frontendUrl));
        configuration.setAllowedMethods(CORS_ALLOWED_METHODS);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(CORS_PATTERN, configuration);
        return source;
    }

}
