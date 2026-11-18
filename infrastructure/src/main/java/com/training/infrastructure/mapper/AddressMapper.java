package com.training.infrastructure.mapper;

import com.training.entity.Address;
import com.training.infrastructure.persistence.address.AddressJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toDomain(AddressJpaEntity entity);

    @Mapping(target = "user", ignore = true)
    AddressJpaEntity toEntity(Address user);

    List<Address> toDomainList(List<AddressJpaEntity> entities);

    @Mapping(target = "user", ignore = true)
    List<AddressJpaEntity> toEntityList(List<Address> addresses);
}
