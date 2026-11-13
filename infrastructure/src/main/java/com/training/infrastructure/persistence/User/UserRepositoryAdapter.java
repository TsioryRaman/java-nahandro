package com.training.infrastructure.persistence.User;

import com.training.interfaces.repository.UserRepository;
import com.training.entity.User;
import com.training.infrastructure.UserJpaRepository;
import com.training.infrastructure.mapper.AddressMapper;
import com.training.infrastructure.mapper.user.UserMapper;
import com.training.utils.PageResult;
import com.training.utils.PaginationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository, UserMapper userMapper, AddressMapper addressMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = new UserJpaEntity(user.getId(), user.getEmail(), user.getUsername());
        entity.setAddress(addressMapper.toEntityList(user.getAddresses()));
        UserJpaEntity saved = userJpaRepository.save(entity);
        return new User(saved.getId(), saved.getEmail(), user.getUsername(), user.getAddresses());
    }


    @Override
    public PageResult<User> getAllUsers(PaginationRequest paginationRequest) throws Exception{
        Pageable pageable = PageRequest.of(paginationRequest.page(), paginationRequest.size());

        Page<UserJpaEntity> result = userJpaRepository.findAllWithAddresses(pageable);
        List<User> users = result.map(userMapper::toDomain).toList();
        return new PageResult<>(
                users,
                result.getTotalElements(),
                result.getTotalPages(),
                result.getNumber(),
                result.getSize()
        );
    }
}
