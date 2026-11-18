package com.training.infrastructure.persistence.role;

import com.training.infrastructure.persistence.user.UserRoleJpaEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(
            mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<UserRoleJpaEntity> userRole = new HashSet<UserRoleJpaEntity>();

    @OneToMany(
            mappedBy = "role"
    )
    private Set<RolePermissionJpaEntity> permission = new HashSet<RolePermissionJpaEntity>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<UserRoleJpaEntity> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRoleJpaEntity> userRole) {
        this.userRole = userRole;
    }

    public Set<RolePermissionJpaEntity> getPermission() {
        return permission;
    }

    public void setPermission(Set<RolePermissionJpaEntity> permission) {
        this.permission = permission;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
