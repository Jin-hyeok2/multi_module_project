package com.example.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberCreateRequest {

    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    @Pattern(regexp = "^[0-9]{4}\\.[0-9]{2}\\.[0-9]{2}$")
    private String birth;
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:[0-9]{3}|[0-9]{4})-[0-9]{4}$")
    private String phoneNumber;
    @NotNull
    private String gender;
    @NotNull
    private String signUpFrom;

}
