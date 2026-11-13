package com.training.infrastructure.service;

import com.training.JwtServiceProvider;
import com.training.dto.CreateAuthDto;
import com.training.dto.auth.AuthJwtPayloadDto;
import com.training.dto.auth.AuthResponse;
import com.training.dto.auth.LoginDto;
import com.training.entity.User;
import com.training.exception.ConflictException;
import com.training.infrastructure.UserJpaRepository;
import com.training.infrastructure.persistence.User.UserJpaEntity;
import com.training.interfaces.security.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtServiceProvider jwtServiceProvider;

    public AuthServiceImpl(UserJpaRepository userJpaRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtServiceProvider jwtServiceProvider) {
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtServiceProvider = jwtServiceProvider;
    }

    @Override
    public void register(CreateAuthDto createAuthDto) {
        var existingUser = userJpaRepository.findByEmail(createAuthDto.email());

        if(existingUser.isPresent()){
            throw new ConflictException("User with email already exists");
        }

        var user = new User(null, createAuthDto.email(),createAuthDto.username(),new ArrayList<>());

        var userJpa = new UserJpaEntity(null,user.getEmail(),user.getUsername());
        String passwordHashed = passwordEncoder.encode(createAuthDto.password());
        userJpa.setPasswordHash(passwordHashed);

        userJpaRepository.save(userJpa);
        userJpaRepository.flush();

    }

    @Override
    public AuthResponse login(LoginDto loginDto) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.email(),
                                loginDto.password()
                        )
                );

        var authJwtPayload = new AuthJwtPayloadDto(authentication.getName(), loginDto.email(), loginDto.email());

        var token = this.jwtServiceProvider.generateJwt(authJwtPayload);

        return new AuthResponse(token,"JWT");
    }
}
