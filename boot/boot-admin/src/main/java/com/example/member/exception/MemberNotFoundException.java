package com.example.member.exception;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends BaseException{

    private MemberNotFoundException(String reason, HttpStatus httpStatus) {
        super(reason, httpStatus);
    }

    public static MemberNotFoundException of() {
        return new MemberNotFoundException(
            "member not found",
            HttpStatus.NOT_FOUND
        );
    }
}
