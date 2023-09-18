package com.example.dto.request;

import com.example.utility.EmailPattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {

    @EmailPattern
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotNull
    @Pattern(regexp = "^[0-9]{4}\\.[0-9]{2}\\.[0-9]{2}$")
    private String birth;
    @NotNull
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:[0-9]{3}|[0-9]{4})-[0-9]{4}$")
    private String phoneNumber;
    private String roles;
}
