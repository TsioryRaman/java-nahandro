package com.training.utils;

import java.util.List;

public record PageResult<T>(
        List<T> content,
        long totalElements,
        int totalPages,
        int currentPage,
        int pageSize
) {}