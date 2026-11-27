package jku.at.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Titel in Swagger und der "Authorize"-Button, mit dem man sich über Keycloak einloggt
@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME = "keycloak";

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    @Bean
    public OpenAPI noteWiseOpenApi() {
        OAuthFlow authorizationCode = new OAuthFlow()
                .authorizationUrl(issuerUri + "/protocol/openid-connect/auth")
                .tokenUrl(issuerUri + "/protocol/openid-connect/token");

        return new OpenAPI()
                .info(new Info()
                        .title("NoteWise API")
                        .description("REST-API für NoteWise: Kurse, Dokumente, Zusammenfassungen, Karteikarten und Chat")
                        .version("0.1.0"))
                .components(new Components().addSecuritySchemes(SECURITY_SCHEME, new SecurityScheme()
                        .type(SecurityScheme.Type.OAUTH2)
                        .flows(new OAuthFlows().authorizationCode(authorizationCode))))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME));
    }
}
