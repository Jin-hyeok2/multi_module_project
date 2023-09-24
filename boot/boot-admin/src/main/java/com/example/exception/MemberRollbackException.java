package com.example.exception;

import com.example.entity.Member;
import org.springframework.http.HttpStatus;

public class MemberRollbackException extends BaseRollbackException {

    private MemberRollbackException(String reason, HttpStatus httpStatus) {
        super(reason, httpStatus);
    }


    public static MemberRollbackException emailNotFound() {
        return new MemberRollbackException(
            "email not found",
            HttpStatus.NOT_FOUND
        );
    }

    public static MemberRollbackException passwordNotMatch() {
        return new MemberRollbackException(
            "password not match",
            HttpStatus.BAD_REQUEST
        );
    }

    public static void duplicated(Member ignoredMember) {
        throw new MemberRollbackException(
            "email duplication",
            HttpStatus.CONFLICT
        );
    }

    public static MemberRollbackException expiredToken() {
        return new MemberRollbackException(
            "expired token",
            HttpStatus.UNAUTHORIZED
        );
    }

    public static MemberRollbackException invalidToken() {
        return new MemberRollbackException(
            "invalid token",
            HttpStatus.BAD_REQUEST
        );
    }

    public static MemberRollbackException notMatchVerificationKey() {
        return new MemberRollbackException(
            "not match verification key",
            HttpStatus.NOT_ACCEPTABLE
        );
    }
}
