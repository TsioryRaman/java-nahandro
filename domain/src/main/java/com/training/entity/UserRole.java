package com.training.entity;

import java.time.LocalDateTime;

public class UserRole {
    private long id;

    private User user;

    private Role role;

    private LocalDateTime assignedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
