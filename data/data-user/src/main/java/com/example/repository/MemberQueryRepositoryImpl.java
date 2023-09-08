package com.example.repository;

import static com.example.entity.QMember.member;
import static com.example.entity.QMemberRole.memberRole;

import com.example.entity.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Member> findOne(BooleanExpression... expressions) {
        return Optional.ofNullable(jpaQueryFactory.select(member)
            .from(memberRole)
            .leftJoin(memberRole.member, member)
            .where(expressions)
            .fetchOne());
    }

    @Override
    public Optional<Member> findTop(String orderFiled, boolean asc,
        BooleanExpression... expressions) {
        PathBuilder<Member> entityPath = new PathBuilder<>(Member.class, "member");

        return Optional.ofNullable(jpaQueryFactory.selectFrom(member)
            .where(expressions)
            .orderBy(asc ? entityPath.getString(orderFiled).asc() :
                entityPath.getString(orderFiled).desc())
            .fetchFirst());
    }

    @Override
    public List<Member> findAll(BooleanExpression... expressions) {
        return jpaQueryFactory.selectFrom(member)
            .where(expressions)
            .fetch();
    }
}
