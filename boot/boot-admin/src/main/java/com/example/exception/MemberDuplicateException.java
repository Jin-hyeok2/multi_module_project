package com.example.exception;

import com.example.dto.response.MemberResponse;
import com.example.entity.Member;
import org.springframework.http.HttpStatus;

public class MemberDuplicateException extends BaseException {

    private MemberDuplicateException(
        String reason,
        HttpStatus httpStatus,
        MemberResponse memberResponse) {
        super(reason, httpStatus, memberResponse);
    }

    public static void from(Member member) {
        throw new MemberDuplicateException(
            "duplicated member input",
            HttpStatus.CONFLICT,
            MemberResponse.from(member)
        );
    }
}