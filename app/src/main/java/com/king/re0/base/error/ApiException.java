package com.king.re0.base.error;


import lombok.Getter;
import lombok.Setter;

public class ApiException extends RuntimeException {
    @Setter
    @Getter
    private Integer code ;
    public ApiException(String msg){
        super(msg);
    }
    public ApiException(Integer code,String msg){
        super(msg);
        this.code =code;
    }
}
