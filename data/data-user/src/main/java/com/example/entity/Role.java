package com.example.entity;

import com.example.exception.EnumNotFoundException;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum Role {
    OWNER("대표", true),
    MANAGER("매니저", false),
    DEVELOPER("개발자", true),
    CUSTOMER("가입 고객", false),
    GUEST("손님", false),
    ;

    private final String code;
    private final String description;
    private final Boolean authorization;

    Role(String description, Boolean authorization) {
        this.code = this.name();
        this.description = description;
        this.authorization = authorization;
    }

    public static Role fromCode(String code) throws EnumNotFoundException {
        return Stream.of(values())
            .filter(role -> code.equalsIgnoreCase(role.code))
            .findFirst().orElseThrow(EnumNotFoundException::code);
    }
}
