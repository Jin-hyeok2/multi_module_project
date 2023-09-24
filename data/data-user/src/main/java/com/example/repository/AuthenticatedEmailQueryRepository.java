package com.example.repository;

import com.example.entity.AuthenticatedEmail;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.Optional;

public interface AuthenticatedEmailQueryRepository {

    Optional<AuthenticatedEmail> findOne(BooleanExpression... expressions);

}
