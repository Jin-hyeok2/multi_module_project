package com.example.entity;

import com.example.exception.EnumNotFoundException;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum SignUpPlatform {

    DEFAULT("기본 가입"), KAKAO("카카오 가입"), GOOGLE("구글 가입");

    private final String code;
    private final String description;

    SignUpPlatform(String description) {
        this.code = this.name();
        this.description = description;
    }

    public static SignUpPlatform fromCode(String code) {
        return Stream.of(values())
            .filter(signUpPlatform -> code.equalsIgnoreCase(signUpPlatform.code))
            .findFirst().orElseThrow(EnumNotFoundException::code);
    }

}
