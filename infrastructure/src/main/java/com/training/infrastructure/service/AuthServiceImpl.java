package com.training.infrastructure.service;

import com.training.JwtServiceProvider;
import com.training.dto.CreateAuthDto;
import com.training.dto.auth.AuthJwtPayloadDto;
import com.training.dto.auth.AuthResponse;
import com.training.dto.auth.LoginDto;
import com.training.entity.Role;
import com.training.entity.User;
import com.training.entity.UserRole;
import com.training.exception.ConflictException;
import com.training.infrastructure.mapper.RoleMapper;
import com.training.infrastructure.mapper.user.UserMapper;
import com.training.infrastructure.persistence.role.RoleJpaEntity;
import com.training.infrastructure.persistence.user.UserRoleJpaEntity;
import com.training.infrastructure.repository.RoleJpaRepository;
import com.training.infrastructure.repository.UserJpaRepository;
import com.training.infrastructure.persistence.user.UserJpaEntity;
import com.training.interfaces.security.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserJpaRepository userJpaRepository;
    private final RoleJpaRepository roleJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtServiceProvider jwtServiceProvider;
    private final RoleMapper roleMapper;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserJpaRepository userJpaRepository, RoleJpaRepository roleJpaRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtServiceProvider jwtServiceProvider, RoleMapper roleMapper, UserMapper userMapper, UserMapper userMapper1) {
        this.userJpaRepository = userJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtServiceProvider = jwtServiceProvider;
        this.roleMapper = roleMapper;
        this.userMapper = userMapper1;
    }

    @Override
    public void register(CreateAuthDto createAuthDto) {
        var existingUser = userJpaRepository.findByEmail(createAuthDto.email());

        if(existingUser.isPresent()){
            throw new ConflictException("User with email already exists");
        }

        Role role = roleJpaRepository.findByName("USER")
                .map(roleMapper::toDomain)
                .orElseThrow(() -> new RuntimeException(
                        "Default role USER not found"
                ));

        UserRole userRole = new UserRole();
        userRole.setRole(role);

        String passwordHashed = passwordEncoder.encode(createAuthDto.password());

        var user = new User(null, createAuthDto.email(),createAuthDto.username(),new ArrayList<>(), passwordHashed, new HashSet<>(Set.of(userRole)));

        var userJpa = userMapper.toEntity(user);

        // The mapper does not set the owning side of the user_roles relation,
        // so wire it here before cascading the insert.
        userJpa.getUserRole().forEach(userRoleJpa -> userRoleJpa.setUser(userJpa));

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
