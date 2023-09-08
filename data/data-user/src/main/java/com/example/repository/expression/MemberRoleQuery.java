package com.example.repository.expression;

import static com.example.entity.QMemberRole.memberRole;

import com.example.entity.Role;
import com.querydsl.core.types.dsl.BooleanExpression;

public class MemberRoleQuery {

    public static BooleanExpression eqRole(Role role) {
        return role == null  ? null :
            memberRole.role.eq(role);
    }

}
