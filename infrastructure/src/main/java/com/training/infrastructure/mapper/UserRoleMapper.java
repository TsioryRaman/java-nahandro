package com.training.infrastructure.mapper;

import com.training.entity.UserRole;
import com.training.infrastructure.mapper.user.UserMapper;
import com.training.infrastructure.persistence.user.UserRoleJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
uses = {
        UserMapper.class,
        RoleMapper.class
})
public interface UserRoleMapper {
    UserRole toDomain(UserRoleJpaEntity entity);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    UserRoleJpaEntity toEntity(UserRole domain);
}
