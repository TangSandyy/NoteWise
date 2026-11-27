package jku.at.dto;

import jakarta.validation.constraints.NotBlank;

// Daten, die das Frontend zum Anlegen oder Umbenennen eines Kurses schickt
public record CourseRequest(@NotBlank String name) {
}
