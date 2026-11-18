package com.training.infrastructure.mapper;

import com.training.entity.UserRole;
import com.training.infrastructure.mapper.user.UserMapper;
import com.training.infrastructure.persistence.user.UserRoleJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
uses = {
        UserMapper.class,
        RoleMapper.class
})
public interface UserRoleMapper {
    UserRole toDomain(UserRoleJpaEntity entity);

    UserRoleJpaEntity toEntity(UserRole domain);
}
