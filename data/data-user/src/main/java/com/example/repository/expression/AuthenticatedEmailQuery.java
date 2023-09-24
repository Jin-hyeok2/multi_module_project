package com.example.repository.expression;

import static com.example.entity.QAuthenticatedEmail.authenticatedEmail;

import com.querydsl.core.types.dsl.BooleanExpression;
import io.micrometer.common.util.StringUtils;
import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthenticatedEmailQuery {

    public BooleanExpression eqEmail(String email) {
        return StringUtils.isBlank(email) ? null :
            authenticatedEmail.email.eq(email);
    }

    public BooleanExpression notExpired() {
        return authenticatedEmail.verifyExpiredAt.after(LocalDateTime.now());
    }

}
