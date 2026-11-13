package com.training.dto.auth;

public record AuthResponse(String accessToken,
                           String tokenType) {
}
