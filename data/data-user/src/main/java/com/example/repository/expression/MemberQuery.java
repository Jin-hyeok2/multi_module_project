package com.example.repository.expression;

import static com.example.entity.QMember.member;

import com.querydsl.core.types.dsl.BooleanExpression;
import io.micrometer.common.util.StringUtils;

public class MemberQuery {

    public static BooleanExpression eqEmail(String email) {
        return StringUtils.isBlank(email) ? null :
            member.email.eq(email);
    }

    public static BooleanExpression eqName(String name) {
        return StringUtils.isBlank(name) ? null :
            member.name.eq(name);
    }

    public static BooleanExpression eqPassword(String password) {
        return StringUtils.isBlank(password) ? null :
            member.password.eq(password);
    }
}
