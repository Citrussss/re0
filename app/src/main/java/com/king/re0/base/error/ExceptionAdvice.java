package com.king.re0.base.error;

import com.king.re0.base.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;


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
                .message(String.format(Locale.CHINA,"%1s>>>%2s",e.toString(),e.getMessage()))
                .build();
    }
}
