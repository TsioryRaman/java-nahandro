package com.training.infrastructure;

import com.training.infrastructure.persistence.role.RolePermissionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionJpaRepository extends JpaRepository<RolePermissionJpaEntity, Long> {
    boolean existsByRoleIdAndPermissionId(Long id, Long id1);
}
