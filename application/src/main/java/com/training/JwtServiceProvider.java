package com.training;

import com.training.dto.auth.AuthJwtPayloadDto;

public interface JwtServiceProvider {
    String generateJwt(AuthJwtPayloadDto authJwtPayloadDto);
}
