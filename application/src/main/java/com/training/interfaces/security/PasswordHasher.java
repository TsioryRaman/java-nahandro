package com.training.interfaces.security;

public interface PasswordHasher {
    String hash(String password);

    boolean matches(String hashedPassword, String password);
}
