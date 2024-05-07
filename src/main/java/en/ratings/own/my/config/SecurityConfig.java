package en.ratings.own.my.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private static final int PASSWORD_STRENGTH = 16;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return deactivateDefaultAuthentication(http);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(PASSWORD_STRENGTH);
    }

    private SecurityFilterChain deactivateDefaultAuthentication(HttpSecurity http) throws Exception {
        return http.httpBasic(Customizer.withDefaults()).
                csrf(AbstractHttpConfigurer::disable).
                build();
    }
}
