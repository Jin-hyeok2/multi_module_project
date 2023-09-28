package com.example.service;

import com.example.entity.FirstMenu;
import com.example.entity.SecondMenu;
import com.example.repository.SecondMenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class SecondMenuService {

    private final SecondMenuRepository secondMenuRepository;

    public void create(FirstMenu firstMenu, String title, String vuePath, String uri) {
        secondMenuRepository.save(SecondMenu.builder()
            .title(title)
            .vuePath(vuePath)
            .uri(uri)
            .firstMenu(firstMenu)
            .build());
    }

}
