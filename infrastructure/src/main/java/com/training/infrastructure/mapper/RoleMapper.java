package com.training.infrastructure.mapper;

import com.training.entity.Role;
import com.training.infrastructure.persistence.role.RoleJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
uses = {
        UserRoleMapper.class,
        PermissionMapper.class
})
public interface RoleMapper {
    Role toDomain(RoleJpaEntity entity);

    @Mapping(target = "userRole", ignore = true)
    RoleJpaEntity toEntity(Role entity);
}
