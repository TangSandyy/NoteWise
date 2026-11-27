package jku.at.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// Hier wird festgelegt, welche Endpoints einen Login brauchen
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // nimmt die Einstellungen aus CorsConfig, damit das Frontend zugreifen darf
                .cors(Customizer.withDefaults())
                // CSRF brauchen wir nicht, weil wir mit Tokens statt Cookies arbeiten
                .csrf(csrf -> csrf.disable())
                // keine Session, das Token wird bei jeder Anfrage mitgeschickt
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Swagger und Health-Check gehen auch ohne Login
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/actuator/health/**", "/error").permitAll()
                        // für alles andere braucht man ein gültiges Token
                        .anyRequest().authenticated())
                // prüft das Token von Keycloak (Adresse steht in application.properties)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
