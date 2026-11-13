package com.training.breakfast.controller;

import com.training.breakfast.dto.auth.CreateAuthUserDtoRequest;
import com.training.breakfast.dto.auth.LoginDtoRequest;
import com.training.breakfast.mapper.UserRequestMapper;
import com.training.dto.auth.AuthResponse;
import com.training.dto.auth.LoginDto;
import com.training.interfaces.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRequestMapper userRequestMapper;

    public AuthController(AuthService authService, UserRequestMapper userRequestMapper) {
        this.authService = authService;
        this.userRequestMapper = userRequestMapper;
    }


    @PostMapping("register")
    public ResponseEntity<Void> register(CreateAuthUserDtoRequest createAuthUserDtoRequest) {
        var userApplication = userRequestMapper.toApplicationDto(createAuthUserDtoRequest);
        authService.register(userApplication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login (LoginDtoRequest loginDtoRequest){
        LoginDto loginDto = userRequestMapper.toApplicationDto(loginDtoRequest);
        var authResponse = authService.login(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }
}
