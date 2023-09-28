package com.example.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SecondMenuCreateRequest {

    @NotBlank
    private String firstMenuTitle;
    @NotBlank
    private String secondMenuTitle;
    @NotBlank
    private String vuePath;
    @NotBlank
    private String uri;

}
