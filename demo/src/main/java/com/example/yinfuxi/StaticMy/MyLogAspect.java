package com.example.yinfuxi.StaticMy;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

//import  com.example.yinfuxi.StaticMy.MyLog;
@Slf4j
@Aspect
@Component
public class MyLogAspect {//通过Mylog注解标识，注解生效的方法

    @Pointcut("@annotation(com.example.yinfuxi.StaticMy.MyLog)")
    private void pt(){}
    @Before("pt()")
    public void before(JoinPoint joinPoint) throws Throwable
    {
        String className=joinPoint.getTarget().getClass().getName();//获取目标类名，全类名的形式
        Signature signature=joinPoint.getSignature();//获取目标方法签名，包含返回值，目标方法名以全类名的形式

        MethodSignature methodSignature=(MethodSignature)joinPoint.getSignature();//方法签名对象
        MyLog mylog=methodSignature.getMethod().getAnnotation(MyLog.class);//获取方法上的注解对象
        OperationType operationType=mylog.value();


        String methodName=joinPoint.getSignature().getName();//获取目标方法名
        Object[] args=joinPoint.getArgs();//获取目标方法运行参数
        log.info(className);
        log.info(signature+"");
        log.info(methodName);
        log.info(args.toString());
        log.info("MyLogAspect方法执行时");
    }
}
