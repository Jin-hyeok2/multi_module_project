package com.example.service;

import com.example.entity.FirstMenu;
import com.example.repository.FirstMenuRepository;
import com.example.repository.query.FirstMenuQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FirstMenuService {

    private final FirstMenuRepository firstMenuRepository;

    public Optional<FirstMenu> findByTitle(String title) {
        return firstMenuRepository.findOne(FirstMenuQuery.eqTitle(title));
    }

    public void create(String title) {
        firstMenuRepository.save(FirstMenu.builder()
            .title(title)
            .build());
    }

    public List<FirstMenu> findAll(String[] title) {
        return firstMenuRepository.findAll(
            FirstMenuQuery.inTitle(title)
        );
    }

}
