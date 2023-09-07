package com.example.dto;

import com.example.entity.SignUpPlatform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchFilter {

    private SignUpPlatform[] signUpPlatforms;

}
