package com.example.allthatbooks.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionResponse> handleCustomException(CustomException e) {
        return getErrorResponse(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String firstErrorMessage = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .findFirst()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .orElseThrow(() -> new IllegalStateException("검증 에러가 반드시 존재해야 합니다."));
        return getErrorResponse(HttpStatus.NOT_FOUND, firstErrorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomExceptionResponse> handleAllExceptions(Exception e) {
        log.error("Exception Handler: {}", e.getMessage());
        return getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ("서버에 오류가 발생했습니다 = " + e.getMessage()));
    }

    private ResponseEntity<CustomExceptionResponse> getErrorResponse(HttpStatus status, String message) {
        CustomExceptionResponse response = CustomExceptionResponse.toResponse(status, message);
        return new ResponseEntity<>(response, status);
    }

}
