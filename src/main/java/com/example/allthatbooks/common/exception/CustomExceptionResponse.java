package com.example.allthatbooks.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class CustomExceptionResponse {

    private final LocalDateTime occurDateTime;
    private final HttpStatus statusCode;
    private final int status;
    private final String message;

    public static CustomExceptionResponse toResponse(HttpStatus status, String message) {
        return new CustomExceptionResponse(
            LocalDateTime.now(),
            status,
            status.value(),
            message
        );
    }

}
