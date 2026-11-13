package com.training.breakfast.dto.user;

import com.training.breakfast.dto.CreateAddressDtoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateUserDtoRequest(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String username,

        @NotEmpty
        List<@Valid CreateAddressDtoRequest> addresses) {
}
