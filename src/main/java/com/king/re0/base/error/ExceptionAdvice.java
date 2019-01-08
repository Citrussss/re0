package com.king.re0.base.error;

import com.king.re0.base.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice

public class ExceptionAdvice {

    @ExceptionHandler(value = ApiException.class)
    @ResponseBody
    public Object ApiException(ApiException e) {
        e.printStackTrace();
        return Result.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object defaultException(Exception e) {
        e.printStackTrace();
        return Result.builder()
                .code(10)
                .message(e.getMessage())
                .build();
    }
}
