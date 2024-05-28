package com.example.yinfuxi.StaticMy;

import com.example.yinfuxi.Service.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Slf4j
@Aspect//说明该类为AOP类
public class TimeAop {


   // @Pointcut("execution(* com.example.yinfuxi.Control.Control.*(..))")
    @Pointcut("execution(* com.example.yinfuxi.Control.Control.selectAll())")
    public void pt(){}
    @Before("pt()")
    public void testAop(JoinPoint proceedingJoinPoint) throws Throwable {
        log.info("原始方法执行前");
    }

    @Around("pt()")//第一个*号表示返回值，第二个*号表示类名,这里变成了Control，第三个*号表示方法名
    public Object recordTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed();//原始方法执行，获取返回值
        long end = System.currentTimeMillis();
        log.info(proceedingJoinPoint.getSignature() + "执行耗时：{}ms", (end - begin));
        return object;
    }


}
