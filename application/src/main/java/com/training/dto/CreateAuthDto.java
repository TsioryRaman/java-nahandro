package com.training.dto;

public record CreateAuthDto(
        String email,
        String password,
        String username
) {
}
