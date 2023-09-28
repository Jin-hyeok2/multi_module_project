package com.example.repository;

import com.example.entity.SecondMenu;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.List;
import java.util.Optional;

public interface SecondMenuQueryRepository {

    Optional<SecondMenu> findOne(BooleanExpression... expressions);

    List<SecondMenu> findAll(BooleanExpression... expressions);

}
