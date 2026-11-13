package com.training.breakfast.controller;

import com.training.breakfast.dto.user.CreateUserDtoRequest;
import com.training.breakfast.mapper.UserRequestMapper;
import com.training.entity.User;
import com.training.interfaces.UserService;
import com.training.utils.PageResult;
import com.training.utils.PaginationRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRequestMapper userRequestMapper;

    public UserController(UserService userService, UserRequestMapper userRequestMapper) {
        this.userService = userService;
        this.userRequestMapper = userRequestMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody CreateUserDtoRequest createUserDtoRequest) {
        var addresses = this.userRequestMapper.toDomainList(createUserDtoRequest.addresses());
        return userService.createUser(createUserDtoRequest.email(), createUserDtoRequest.username(), addresses);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PageResult<User>> getAllUsers(Pageable pageable) throws Exception {
        PaginationRequest paginationRequest = new PaginationRequest(pageable.getPageNumber(), pageable.getPageSize());
        return ResponseEntity.ok(userService.getAllUser(paginationRequest));
    }

    @GetMapping("/search/{search}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<PageResult<User>> search(@PathVariable String search,@PageableDefault(
            sort = "email",
            direction = Sort.Direction.DESC
    ) Pageable pageable) {
        PaginationRequest paginationRequest = new PaginationRequest(pageable.getPageNumber(), pageable.getPageSize());
        return ResponseEntity.ok(userService.findByEmailOrByUsername(search, paginationRequest));
    }
}
