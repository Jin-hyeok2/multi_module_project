package com.example.entity;

import com.example.exception.EnumNotFoundException;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum Role {
    OWNER("대표"),
    MANAGER("매니저"),
    DEVELOPER("개발자"),
    CUSTOMER("가입 고객"),
    GUEST("손님"),
    ;

    private final String code;
    private final String description;

    Role(String description) {
        this.code = this.name();
        this.description = description;
    }

    public static Role fromCode(String code) throws EnumNotFoundException {
        return Stream.of(values())
            .filter(role -> code.equalsIgnoreCase(role.code))
            .findFirst().orElseThrow(EnumNotFoundException::code);
    }
}
