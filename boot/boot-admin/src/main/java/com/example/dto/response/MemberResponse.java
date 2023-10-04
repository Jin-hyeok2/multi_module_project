package com.example.dto.response;

import com.example.entity.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class MemberResponse implements Function<Member, MemberResponse> {

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

    @Override
    public MemberResponse apply(Member member) {
        return MemberResponse.builder()
            .email(member.getEmail())
            .name(member.getName())
            .createdAt(member.getCreatedAt())
            .build();
    }
}
