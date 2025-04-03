package com.example.allthatbooks.domain.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // N
    NOT_FOUND_BOOK(HttpStatus.NOT_FOUND, "아이디에 해당하는 책이 없습니다."),

    // U
    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String message;

}
