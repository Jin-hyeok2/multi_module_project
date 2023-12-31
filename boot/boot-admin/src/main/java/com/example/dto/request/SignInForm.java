package com.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInForm {

    private String email;
    private String password;
    private Boolean isRemember;

}
