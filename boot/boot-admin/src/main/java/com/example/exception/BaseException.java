package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class BaseException extends RuntimeException {
    private final String reason;
    private final HttpStatus httpStatus;
}
