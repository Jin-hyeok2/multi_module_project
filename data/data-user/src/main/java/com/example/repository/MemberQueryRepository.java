package com.example.repository;

import com.example.entity.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberQueryRepository {

    Optional<Member> findOne(BooleanExpression... expressions);
    Optional<Member> findTop(String orderFiled, boolean asc, BooleanExpression... expressions);
    List<Member> findAll(BooleanExpression... expressions);

}
