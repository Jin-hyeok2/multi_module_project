package com.example.dto;

import com.example.entity.Role;
import com.example.entity.SignUpPlatform;
import com.example.utility.Utility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchFilter {

    private String email;
    private String name;
    private String password;
    private SignUpPlatform signUpPlatforms;
    private Role roles;

    public String getPassword() {
        return Utility.encodePassword(password);
    }

}
