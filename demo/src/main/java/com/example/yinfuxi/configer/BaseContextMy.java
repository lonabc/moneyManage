package com.example.yinfuxi.configer;

import com.example.yinfuxi.Control.InterceptorController;

public class BaseContextMy {
    private static ThreadLocal<Integer> threadLocal=new ThreadLocal<>();

    private static ThreadLocal<String> threadlocal2=new ThreadLocal<>();

    public static void set(Integer id)
    {
        threadLocal.set(id);
    }
    public static void setstring(String jwtCopy){
        threadlocal2.set(jwtCopy);
    }
    public static Integer get()
    {
        return threadLocal.get();
    }
    public static String get2()
    {
        return threadlocal2.get();
    }

    public static void remove()
    {
        threadLocal.remove();
    }
}
