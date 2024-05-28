package com.example.yinfuxi.StaticMy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//指定标识生效时间，这里是运行时
@Target(ElementType.METHOD)//指定标识生效范围，这里指的是方法
public @interface MyLog {//由于该类暂时只是起个标识的作用，所以内部暂时无需内容
   OperationType value();
}
