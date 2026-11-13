package com.training.breakfast.mapper;

import com.training.breakfast.dto.CreateAddressDtoRequest;
import com.training.breakfast.dto.auth.CreateAuthUserDtoRequest;
import com.training.breakfast.dto.user.CreateUserDtoRequest;
import com.training.dto.CreateAuthDto;
import com.training.entity.Address;
import com.training.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {
    User toDomain(CreateUserDtoRequest request);

    Address toDomain(CreateAddressDtoRequest request);

    List<Address> toDomainList(List<CreateAddressDtoRequest> requests);

    CreateAuthDto toApplicationDto(CreateAuthUserDtoRequest request);
}
