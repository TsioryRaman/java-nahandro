package com.training.utils;

public record PaginationRequest (
        int page,
        int size) {
}
