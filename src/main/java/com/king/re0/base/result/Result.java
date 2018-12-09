package com.king.re0.base.result;

import com.king.re0.base.entity.InfoEntity;

public class Result<T> {
    private Result() {}
    private Result(InfoEntity<T> infoEntity){
        this.infoEntity =infoEntity;
    }
    private InfoEntity<T> infoEntity;
    public static <E>Result<E> builder(){
        return new Result<>(new InfoEntity<E>());
    }
    public Result<T> code(int code){
        infoEntity.setCode(code);
        return this;
    }
    public Result<T> message(String message){
        infoEntity.setMsg(message);
        return this;
    }
    public Result<T> data(T t){
        infoEntity.setData(t);
        return this;
    }
    public InfoEntity<T> build(){
        return infoEntity;
    }
}
