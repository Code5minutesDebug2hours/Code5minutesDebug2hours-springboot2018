package com.zxf.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author zxf
 */
@Aspect
@Component
public class UserAop {
    @Pointcut("execution(* com.zxf.demo.model.User.toString(..))")
    public void zxf(){}

    @Before(value = "zxf()")
    public void before(JoinPoint joinPoint){
        System.out.println("before:"+joinPoint.toString());
    }
    @After("zxf()")
    public void after(JoinPoint joinPoint){
        System.out.println("after testMap.."+joinPoint.toString());
    }
    @AfterReturning("zxf()")
    public void afterReturn(JoinPoint  joinPoint){
        System.out.println("afterReturn.."+joinPoint.toString());
    }

    @Around("zxf()")
    public Object around(ProceedingJoinPoint p) {
        System.out.println("环绕拦截前");
        Object obj = null;
        try {
            //调用执行目标方法并得到执行方法的返回值
            obj = p.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("环绕拦截执行方法出错");
        }
        System.out.println("环绕拦截后");
        return obj;
    }
}
