package com.example.repository;

import com.example.entity.FirstMenu;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstMenuRepository extends
    JpaRepository<FirstMenu, UUID>,
    FirstMenuQueryRepository {

}
