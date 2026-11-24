package jku.at.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Titel und Beschreibung füpr Swagger-Oberfläche
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI noteWiseOpenApi() {
        return new OpenAPI().info(new Info()
                .title("NoteWise API")
                .description("REST-API für NoteWise: Kurse, Dokumente, Zusammenfassungen, Karteikarten und Chat")
                .version("0.1.0"));
    }
}
