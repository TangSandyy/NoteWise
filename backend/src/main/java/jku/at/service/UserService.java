package jku.at.service;

import jku.at.entity.User;
import jku.at.repository.UserRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Holt den User zum Token,
    public User getCurrentUser(Jwt jwt) {
        String keycloakId = jwt.getSubject();
        String username = jwt.getClaimAsString("preferred_username");

        return userRepository.findByKeycloakId(keycloakId)
                // schon mal eingeloggt: User ist bereits verknüpft
                .orElseGet(() -> userRepository.findByUsername(username)
                        // User gibt es schon (z. B. aus den Testdaten), aber noch ohne Keycloak-ID
                        .map(existing -> {
                            existing.setKeycloakId(keycloakId);
                            return existing;
                        })
                        // ganz neuer User, z. B. nach der Registrierung in Keycloak
                        .orElseGet(() -> {
                            User user = new User();
                            user.setKeycloakId(keycloakId);
                            user.setUsername(username);
                            return userRepository.save(user);
                        }));
    }
}
