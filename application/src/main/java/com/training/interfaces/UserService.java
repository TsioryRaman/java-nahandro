package com.training.interfaces;


import com.training.entity.Address;
import com.training.entity.User;
import com.training.utils.PageResult;
import com.training.utils.PaginationRequest;

import java.util.List;

public interface UserService {
    User createUser(String email, String username, List<Address> addresses);

    PageResult<User> getAllUser(PaginationRequest paginationRequest) throws Exception;

    PageResult<User> findByEmailOrByUsername(String search, PaginationRequest pageable);
}
