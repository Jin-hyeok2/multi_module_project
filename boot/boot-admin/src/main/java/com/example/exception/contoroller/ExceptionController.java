package com.example.exception.contoroller;

import com.example.exception.BaseException;
import com.example.dto.response.BaseResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BaseException.class)
    public BaseResponseEntity responseException(BaseException e) {
        return BaseResponseEntity.fail(e);
    }

    @ExceptionHandler(Exception.class)
    public BaseResponseEntity responseException(Exception ignoredE) {
        return BaseResponseEntity.fail();
    }
}
