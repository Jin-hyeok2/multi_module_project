package com.example.member.response;

import com.example.entity.Member;
import com.example.entity.SignUpPlatform;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class MemberResponse extends BaseResponse {

    private final String email;
    private final String name;
    private final SignUpPlatform signUpPlatform;
    private final LocalDateTime createdAt;

    @Builder
    private MemberResponse(UUID id, String email, String name, SignUpPlatform signUpPlatform, LocalDateTime createdAt) {
        this.email = email;
        this.name = name;
        this.signUpPlatform = signUpPlatform;
        this.createdAt = createdAt;

    }

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
            .id(member.getId())
            .email(member.getEmail())
            .name(member.getName())
            .signUpPlatform(member.getSignUpPlatform())
            .createdAt(member.getCreatedAt())
            .build();
    }

}
