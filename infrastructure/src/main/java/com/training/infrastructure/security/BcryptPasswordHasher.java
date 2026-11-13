package com.training.infrastructure.security;

import com.training.interfaces.security.PasswordHasher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptPasswordHasher implements PasswordHasher {
    private final PasswordEncoder passwordEncoder;

    public BcryptPasswordHasher(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String hash(String password) {
        return this.passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String hashedPassword, String password) {
        return this.passwordEncoder.matches(password, hashedPassword);
    }
}
