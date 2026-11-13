package com.training.dto.auth;

public record LoginDto(
        String email,
        String password
) {
}
