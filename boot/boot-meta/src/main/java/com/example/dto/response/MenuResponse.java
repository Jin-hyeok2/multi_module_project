package com.example.dto.response;

import com.example.entity.FirstMenu;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record MenuResponse(UUID id, String title, List<SecondMenuResponseProperty> secondMenus) {

    public static MenuResponse from(FirstMenu firstMenu) {
        return MenuResponse.builder()
            .id(firstMenu.getId())
            .title(firstMenu.getTitle())
            .secondMenus(firstMenu.getSecondMenus()
                .stream()
                .map(SecondMenuResponseProperty::from)
                .collect(Collectors.toList()))
            .build();
    }

    public BaseResponse toResponse() {
        return new BaseResponse(this);
    }
}
