package com.training.breakfast.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAddressDtoRequest(
        @NotBlank
        String name
) {
}
