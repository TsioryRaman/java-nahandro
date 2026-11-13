package com.training.breakfast.dto.auth;

public record LoginDtoRequest(
        String email,
        String password
) {
}
