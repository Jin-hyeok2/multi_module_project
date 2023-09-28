package com.example.service;

import com.example.entity.FirstMenu;
import com.example.exception.FirstMenuRollbackException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    public final FirstMenuService firstMenuService;
    private final SecondMenuService secondMenuService;


    public void createFirstMenu(String title) {
        firstMenuService.create(title);
    }

    public void createSecondMenu(
        String firstMenuTitle,
        String secondMenuTitle,
        String vuePath,
        String uri) {
        FirstMenu firstMenu = firstMenuService.findByTitle(firstMenuTitle)
            .orElseThrow(FirstMenuRollbackException::notFound);
        secondMenuService.create(
            firstMenu,
            secondMenuTitle,
            vuePath,
            uri
        );
    }

}
