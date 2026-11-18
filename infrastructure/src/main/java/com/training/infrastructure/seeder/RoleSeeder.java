package com.training.infrastructure.seeder;

import com.training.infrastructure.PermissionJpaRepository;
import com.training.infrastructure.RoleJpaRepository;
import com.training.infrastructure.RolePermissionJpaRepository;
import com.training.infrastructure.persistence.permission.PermissionJpaEntity;
import com.training.infrastructure.persistence.role.RoleJpaEntity;
import com.training.infrastructure.persistence.role.RolePermissionJpaEntity;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Profile("dev")
public class RoleSeeder {

    private final PermissionJpaRepository permissionRepository;
    private final RoleJpaRepository roleRepository;
    private final RolePermissionJpaRepository rolePermissionRepository;

    public RoleSeeder(PermissionJpaRepository permissionRepository, RoleJpaRepository roleRepository, RolePermissionJpaRepository rolePermissionRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Bean
    public CommandLineRunner initializeSecurityData() {
        return args -> {

            // 1. Seed permissions
            Map<String, PermissionJpaEntity> permissions = new HashMap<>();

            for (String name : List.of(
                    "USER_READ",
                    "USER_CREATE",
                    "USER_UPDATE",
                    "USER_DELETE",
                    "ROLE_READ",
                    "ROLE_CREATE",
                    "ROLE_UPDATE",
                    "ROLE_DELETE"
            )) {
                PermissionJpaEntity permission =
                        permissionRepository.findByName(name)
                                .orElseGet(() -> {
                                    PermissionJpaEntity entity =
                                            new PermissionJpaEntity();
                                    entity.setName(name);
                                    return permissionRepository.save(entity);
                                });

                permissions.put(name, permission);
            }

            // 2. Seed roles
            RoleJpaEntity admin = getOrCreateRole("ADMIN");
            RoleJpaEntity user = getOrCreateRole("USER");

            // 3. Assign permissions to ADMIN
            assignPermissions(admin, permissions.values());

            // 4. Assign permissions to USER
            assignPermissions(user, List.of(
                    permissions.get("USER_READ")
            ));
        };
    }

    private RoleJpaEntity getOrCreateRole(String name) {
        return roleRepository.findByName(name)
                .orElseGet(() -> {
                    RoleJpaEntity role = new RoleJpaEntity();
                    role.setName(name);
                    return roleRepository.save(role);
                });
    }

    private void assignPermissions(
            RoleJpaEntity role,
            Collection<PermissionJpaEntity> permissions
    ) {
        for (PermissionJpaEntity permission : permissions) {
            boolean exists = rolePermissionRepository
                    .existsByRoleIdAndPermissionId(
                            role.getId(),
                            permission.getId()
                    );

            if (!exists) {
                RolePermissionJpaEntity association =
                        new RolePermissionJpaEntity();

                association.setRole(role);
                association.setPermission(permission);

                rolePermissionRepository.save(association);
            }
        }
    }
}
