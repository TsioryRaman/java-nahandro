package com.training.breakfast.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateAuthUserDtoRequest(
        @Email
        @NotBlank
        String email,

        @NotBlank
        String username,

        @NotBlank
        @Min(8)
        String password
) {
}
