package com.example.dto.response;

import com.example.entity.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class MemberResponse extends BaseResponse {

    private final String email;
    private final String name;
    private final LocalDateTime createdAt;

    @Builder(access = AccessLevel.PRIVATE)
    private MemberResponse(
        UUID id,
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
            .id(member.getId())
            .email(member.getEmail())
            .name(member.getName())
            .createdAt(member.getCreatedAt())
            .build();
    }

}
