package com.example.dto.response;

import com.example.entity.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class MemberResponse {

    private final String email;
    private final String name;
    private final LocalDateTime createdAt;

    @Builder(access = AccessLevel.PRIVATE)
    private MemberResponse(
        String email,
        String name,
        LocalDateTime createdAt
    ) {
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
            .email(member.getEmail())
            .name(member.getName())
            .createdAt(member.getCreatedAt())
            .build();
    }

    public BaseResponse toResponse() {
        return new BaseResponse(this);
    }

}
