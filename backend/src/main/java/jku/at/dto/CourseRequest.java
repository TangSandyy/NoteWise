package jku.at.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Daten, die das Frontend zum Anlegen eines Kurses schickt
public record CourseRequest(
        @NotBlank String name,
        @NotNull Long userId // wird mit dem Login durch den eingeloggten User ersetzt
) {
}
