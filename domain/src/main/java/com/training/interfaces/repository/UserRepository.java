package com.training.interfaces.repository;

import com.training.entity.User;
import com.training.utils.PageResult;
import com.training.utils.PaginationRequest;

public interface UserRepository {
    User save(User user);

    PageResult<User> getAllUsers(PaginationRequest paginationRequest) throws Exception;
}
