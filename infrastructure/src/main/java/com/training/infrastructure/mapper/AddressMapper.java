package com.training.infrastructure.mapper;

import com.training.entity.Address;
import com.training.infrastructure.persistence.Adress.AddressJpaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toDomain(AddressJpaEntity entity);

    AddressJpaEntity toEntity(Address user);

    List<Address> toDomainList(List<AddressJpaEntity> entities);

    List<AddressJpaEntity> toEntityList(List<Address> addresses);
}
