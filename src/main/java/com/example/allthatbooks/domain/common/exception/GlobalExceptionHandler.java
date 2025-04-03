package com.example.allthatbooks.domain.common.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public CustomExceptionResponse handleCustomException(CustomException e) {
        return CustomExceptionResponse.toResponse(e.getErrorCode());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String firstErrorMessage = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .findFirst()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .orElseThrow(() -> new IllegalStateException("검증 에러가 반드시 존재해야 합니다."));
        System.out.println("Hello!!! MethodArgumentNotValidException");
        return CustomExceptionResponse.toResponse(ErrorCode.NOT_FOUND_BOOK);
    }
    @ExceptionHandler(Exception.class)
    public CustomExceptionResponse handleAllExceptions(Exception e) {
        return CustomExceptionResponse.toResponse(ErrorCode.UNKNOWN);
    }
}
