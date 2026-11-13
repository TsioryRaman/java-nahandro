package com.training.dto.auth;

public record AuthJwtPayloadDto(
        String id,
        String username,
        String email
) {
}
