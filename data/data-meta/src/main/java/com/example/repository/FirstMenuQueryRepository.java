package com.example.repository;

import com.example.entity.FirstMenu;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.List;
import java.util.Optional;

public interface FirstMenuQueryRepository {

    Optional<FirstMenu> findOne(BooleanExpression... expressions);

    List<FirstMenu> findAll(BooleanExpression... expressions);

}
