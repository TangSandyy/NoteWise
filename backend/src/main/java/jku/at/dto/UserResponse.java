package jku.at.dto;

import jku.at.entity.User;

// Daten vom eingeloggten User, die ans Frontend gehen
public record UserResponse(Long id, String username) {

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getUsername());
    }
}
