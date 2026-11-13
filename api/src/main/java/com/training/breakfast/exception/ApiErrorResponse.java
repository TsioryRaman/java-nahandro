package com.training.breakfast.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiErrorResponse(
        int status,
        String error,
        String message,
        Map<String, String> validationErrors,
        LocalDateTime timestamp
) {

}
