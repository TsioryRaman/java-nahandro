package com.training.interfaces.security;

import com.training.dto.CreateAuthDto;
import com.training.dto.auth.AuthResponse;
import com.training.dto.auth.LoginDto;

public interface AuthService {
    void register(CreateAuthDto createAuthDto);

    AuthResponse login(LoginDto loginDto);
}
