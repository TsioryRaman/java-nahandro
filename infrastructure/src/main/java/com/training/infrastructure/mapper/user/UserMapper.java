package com.training.infrastructure.mapper.user;

import com.training.entity.User;
import com.training.infrastructure.mapper.AddressMapper;
import com.training.infrastructure.persistence.User.UserJpaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
uses = AddressMapper.class)
public interface UserMapper {

    User toDomain(UserJpaEntity entity);

    UserJpaEntity toEntity(User user);

    List<User> toDomainList(List<UserJpaEntity> entities);

    List<UserJpaEntity> toEntityList(List<User> users);
}
