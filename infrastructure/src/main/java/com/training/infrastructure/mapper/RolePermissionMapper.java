package com.training.infrastructure.mapper;

import com.training.entity.RolePermission;
import com.training.infrastructure.persistence.role.RolePermissionJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
uses = {
        RoleMapper.class,
        PermissionMapper.class
})
public interface RolePermissionMapper {
    RolePermission toDomain(RolePermissionJpaEntity entity);

    @Mapping(target = "permission", ignore = true)
    RolePermissionJpaEntity toEntity(RolePermission domain);
}
