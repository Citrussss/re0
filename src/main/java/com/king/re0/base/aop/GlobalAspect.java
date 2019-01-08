package com.king.re0.base.aop;

import com.king.re0.base.result.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static com.king.re0.base.result.ResultCode.SUCCESS;

@Aspect
@Component
public class GlobalAspect implements Ordered {
    @Pointcut("execution(public * com.king.re0.service.*.*(..)) && !execution(public * com.king.re0.service.*.flux*(..)) ")
//    @Pointcut("execution(public * com.king.re0.service.*.flux*(..))")
    public void webLog() {
    }

    @Pointcut("execution(public * com.king.re0.service.*.flux*(..))")
    public void flux() {
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
            // 记录下请求内容
            System.out.println("URL : " + request.getRequestURL().toString());
            System.out.println("HTTP_METHOD : " + request.getMethod());
            System.out.println("IP : " + request.getRemoteAddr());
            System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        System.out.println("方法的返回值 : " + ret);
    }

    //后置异常通知
    @AfterThrowing("webLog()")
    public void throwss(JoinPoint jp) {
        System.out.println("方法异常时执行.....");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint jp) {
        System.out.println("方法最后执行.....");
    }

    //环绕通知,环绕增强，相当于MethodInterceptor
    //**不要对切点进行错误捕捉，否者全局的捕捉方法无法感知错误
    @Around("webLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("方法环绕start.....");
            Object o = pjp.proceed();
            System.out.println("方法环绕proceed，结果是 :" + o);
            return Result.builder().code(SUCCESS).data(o).build();

    }

    @Around("flux()")
    public Object aroundFlux(ProceedingJoinPoint pjp) {
        try {
            Flux o = (Flux) pjp.proceed();
            return o.map(it -> Result.builder().code(SUCCESS).data(it).build());
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getOrder() {
        return 100;
    }
}

