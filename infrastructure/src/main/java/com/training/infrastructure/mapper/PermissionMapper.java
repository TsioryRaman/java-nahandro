package com.training.infrastructure.mapper;

import com.training.entity.Permission;
import com.training.infrastructure.persistence.permission.PermissionJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toDomain(PermissionJpaEntity entity);

    PermissionJpaEntity toEntity(Permission permission);
}
