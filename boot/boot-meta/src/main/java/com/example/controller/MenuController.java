package com.example.controller;

import com.example.dto.request.FirstMenuCreateRequest;
import com.example.dto.request.MenuFinderParam;
import com.example.dto.request.SecondMenuCreateRequest;
import com.example.dto.response.BaseResponse;
import com.example.dto.response.BaseResponseEntity;
import com.example.dto.response.MenuResponse;
import com.example.service.MenuService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MenuController.REQUEST_URI)
@RequiredArgsConstructor
public class MenuController {

    final static String REQUEST_URI = "/menu";

    private final MenuService menuService;
    private MenuResponse menuResponse;

    @GetMapping
    public BaseResponseEntity findAllMenu(@Validated MenuFinderParam param) {
        return BaseResponseEntity.succeed(
            menuService.firstMenuService.findAll(param.getTitles())
                .stream()
                .map(menuResponse)
                .map(BaseResponse::new)
                .collect(Collectors.toList())
        );
    }

    @PostMapping("/first-menu")
    public BaseResponseEntity createFirstMenu(
        @RequestBody @Validated FirstMenuCreateRequest request
    ) {
        menuService.firstMenuService.create(request.getTitle());
        return BaseResponseEntity.succeed();
    }

    @PostMapping("/second-menu")
    public BaseResponseEntity createSecondMenu(
        @RequestBody @Validated SecondMenuCreateRequest request
    ) {
        menuService.createSecondMenu(
            request.getFirstMenuTitle(),
            request.getSecondMenuTitle(),
            request.getVuePath(),
            request.getUri()
        );
        return BaseResponseEntity.succeed();
    }

}
