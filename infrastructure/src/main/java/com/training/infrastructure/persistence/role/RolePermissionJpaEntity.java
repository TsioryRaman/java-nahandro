package com.training.infrastructure.persistence.role;

import com.training.infrastructure.persistence.permission.PermissionJpaEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "role_permissions")
public class RolePermissionJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleJpaEntity role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private PermissionJpaEntity permission;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;

    public void setRole(RoleJpaEntity role) {
        this.role = role;
    }

    public void setPermission(PermissionJpaEntity permission) {
        this.permission = permission;
    }

    public RoleJpaEntity getRole() {
        return role;
    }

    public PermissionJpaEntity getPermission() {
        return permission;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
