package com.example.repository;

import com.example.entity.SecondMenu;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondMenuRepository extends
    JpaRepository<SecondMenu, UUID>,
    SecondMenuQueryRepository {

}
