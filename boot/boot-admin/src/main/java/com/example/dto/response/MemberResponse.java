package com.example.dto.response;

import com.example.entity.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class MemberResponse {

    private String name;

    @Builder
    private MemberResponse(String name) {
        this.name = name;
    }

    public static MemberResponse fromEntity(Member member) {
        return MemberResponse.builder()
            .name(member.getName())
            .build();
    }

}
