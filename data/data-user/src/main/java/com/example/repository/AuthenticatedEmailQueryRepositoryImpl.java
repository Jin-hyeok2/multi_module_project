package com.example.repository;

import static com.example.entity.QAuthenticatedEmail.authenticatedEmail;

import com.example.entity.AuthenticatedEmail;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthenticatedEmailQueryRepositoryImpl implements AuthenticatedEmailQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<AuthenticatedEmail> findOne(BooleanExpression... expressions) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(authenticatedEmail)
            .where(expressions)
            .fetchOne());
    }
}
