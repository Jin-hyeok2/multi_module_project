package com.example.repository.expression;

import static com.example.entity.QMember.member;

import com.example.entity.SignUpPlatform;
import com.querydsl.core.types.dsl.BooleanExpression;

public class MemberQuery {

    public static BooleanExpression inSignUpPlatform(SignUpPlatform... signUpPlatforms) {
        return signUpPlatforms == null || signUpPlatforms.length == 0 ? null :
            member.signUpPlatform.in(signUpPlatforms);
    }

}
