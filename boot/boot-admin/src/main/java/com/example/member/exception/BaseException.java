package com.example.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class BaseException extends RuntimeException {
    private final String reason;
    private final HttpStatus httpStatus;
    private Object data;
}
