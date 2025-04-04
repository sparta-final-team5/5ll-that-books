package com.example.allthatbooks.common.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
public class CustomException extends RuntimeException {
    private final HttpStatus status;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
    }

    public CustomException(ErrorCode errorCode, String message) {
        super(errorCode.getMessage() + " " + message);
        this.status = errorCode.getStatus();
    }

    public CustomException(Exception ex) {
        if (ex.getClass() == CustomException.class) {
            CustomException customException = (CustomException) ex;
            this.status = customException.getStatus();
        } else {
            this.status = HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("", ex);
        }
    }

}
