package com.example.repository.impl;

import static com.example.entity.QFirstMenu.firstMenu;

import com.example.entity.FirstMenu;
import com.example.repository.FirstMenuQueryRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FirstMenuQueryRepositoryImpl implements FirstMenuQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<FirstMenu> findOne(BooleanExpression... expressions) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(firstMenu)
            .leftJoin(firstMenu.secondMenus)
            .fetchJoin()
            .where(expressions)
            .fetchOne());
    }

    @Override
    public List<FirstMenu> findAll(BooleanExpression... expressions) {
        return jpaQueryFactory.selectFrom(firstMenu)
            .leftJoin(firstMenu.secondMenus)
            .fetchJoin()
            .where(expressions)
            .fetch();
    }
}
