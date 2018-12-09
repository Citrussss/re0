package com.king.re0.base.error;

import com.king.re0.base.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice

public class ExceptionAdvice {

    @ExceptionHandler(value = ApiException.class)
    @ResponseBody
    public Object ApiException(HttpServletRequest request, ApiException e) {
        e.printStackTrace();
        return Result.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object defaultException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return Result.builder()
                .code(10)
                .message(e.getMessage())
                .build();
    }
}
