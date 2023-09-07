package com.example.utility;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailPatternValidator implements ConstraintValidator<EmailPattern, String> {

    static String regExp;

    @Override
    public void initialize(EmailPattern constraintAnnotation) {
        regExp = constraintAnnotation.regExp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        return Pattern.compile(regExp).matcher(value).matches();
    }
}
