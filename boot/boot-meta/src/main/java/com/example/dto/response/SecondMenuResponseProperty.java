package com.example.dto.response;

import com.example.entity.SecondMenu;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecondMenuResponseProperty {

    private UUID id;
    private String title;
    private String vuePath;
    private String uri;

    public static SecondMenuResponseProperty from(SecondMenu secondMenu) {
        return SecondMenuResponseProperty.builder()
            .id(secondMenu.getId())
            .title(secondMenu.getTitle())
            .vuePath(secondMenu.getVuePath())
            .uri(secondMenu.getUri())
            .build();
    }

}
