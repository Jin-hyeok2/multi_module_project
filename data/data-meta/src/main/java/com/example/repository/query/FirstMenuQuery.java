package com.example.repository.query;

import static com.example.entity.QFirstMenu.firstMenu;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FirstMenuQuery {

    public BooleanExpression eqTitle(String title) {
        return StringUtils.isNullOrEmpty(title) ? null :
            firstMenu.title.eq(title);
    }

    public BooleanExpression inTitle(String... title) {
        return title == null || title.length == 0 ? null :
            firstMenu.title.in(title);
    }

}
