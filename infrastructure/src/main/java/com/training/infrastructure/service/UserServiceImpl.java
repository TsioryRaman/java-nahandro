package com.training.infrastructure.service;

import com.training.entity.Address;
import com.training.entity.User;
import com.training.infrastructure.UserJpaRepository;
import com.training.infrastructure.mapper.user.UserMapper;
import com.training.interfaces.UserService;
import com.training.interfaces.repository.UserRepository;
import com.training.utils.PageResult;
import com.training.utils.PaginationRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserJpaRepository userJpaRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User createUser(String email, String username, List<Address> addresses) {
        var user = new User(null, email, username, addresses);
        return userRepository.save(user);
    }

    @Override
    public PageResult<User> getAllUser(PaginationRequest paginationRequest) throws Exception {
        return userRepository.getAllUsers(paginationRequest);
    }

    @Override
    public PageResult<User> findByEmailOrByUsername(String search, PaginationRequest paginationRequest) {
        Pageable pageable = PageRequest.of(paginationRequest.page(), paginationRequest.size());
        var result = userJpaRepository.findByEmailContainingIgnoreCaseOrUsernameContainingIgnoreCase(search, search, pageable);

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
