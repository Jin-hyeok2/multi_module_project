package com.example.repository.impl;

import static com.example.entity.QSecondMenu.secondMenu;

import com.example.entity.SecondMenu;
import com.example.repository.SecondMenuQueryRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SecondMenuQueryRepositoryImpl implements SecondMenuQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<SecondMenu> findOne(BooleanExpression... expressions) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(secondMenu)
            .innerJoin(secondMenu.firstMenu)
            .fetchJoin()
            .where(expressions)
            .fetchOne());
    }

    @Override
    public List<SecondMenu> findAll(BooleanExpression... expressions) {
        return jpaQueryFactory.selectFrom(secondMenu)
            .innerJoin(secondMenu.firstMenu)
            .fetchJoin()
            .where(expressions)
            .fetch();
    }
}
