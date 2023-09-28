package com.example.exception;

import org.springframework.http.HttpStatus;

public class FirstMenuRollbackException extends BaseRollbackException {

    private FirstMenuRollbackException(String reason, HttpStatus httpStatus) {
        super(reason, httpStatus);
    }

    public static FirstMenuRollbackException notFound() {
        return new FirstMenuRollbackException(
            "first menu not found",
            HttpStatus.NOT_FOUND
        );
    }
}
