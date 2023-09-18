package com.example.member.exception;

import com.example.entity.Member;
import org.springframework.http.HttpStatus;

public class MemberException extends BaseException {

    private MemberException(String reason, HttpStatus httpStatus) {
        super(reason, httpStatus);
    }


    public static MemberException emailNotFound() {
        return new MemberException(
            "member not found",
            HttpStatus.NOT_FOUND
        );
    }

    public static MemberException passwordNotMatch() {
        return new MemberException(
            "password not match",
            HttpStatus.BAD_REQUEST
        );
    }

    public static void duplicated(Member ignoredMember) {
        throw new MemberException(
            "email duplication",
            HttpStatus.CONFLICT
        );
    }
}
