package com.example.utility;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailPatternValidator.class)
public @interface EmailPattern {

    String regExp() default "[a-zA-Z0-9]+[@][a-zA-Z]+\\.(?:[a-zA-Z]{3}|[a-zA-Z]{2}\\.[a-zA-Z]{2})";

    Class<?>[] groups() default { };

    /**
     * @return the payload associated to the constraint
     */
    Class<? extends Payload>[] payload() default { };
}
