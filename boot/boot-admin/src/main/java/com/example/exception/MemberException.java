package com.example.exception;

import org.springframework.http.HttpStatus;

public class MemberException extends BaseException {

    private MemberException(String reason, HttpStatus httpStatus) {
        super(reason, httpStatus);
    }

    public static MemberException failMailSend(String email) {
        return new MemberException(
            "fail sending mail to " + email,
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
