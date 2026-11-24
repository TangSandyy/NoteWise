package jku.at.dto;

import jakarta.validation.constraints.NotBlank;

// Daten zum Umbenennen eines Kurses
public record CourseUpdateRequest(@NotBlank String name) {
}
