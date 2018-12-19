package com.king.re0.base.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TokenAspect {

    @Pointcut("execution(public * com.king.re0.service.*.*(..))")
    public void webLog() {
    }
}
