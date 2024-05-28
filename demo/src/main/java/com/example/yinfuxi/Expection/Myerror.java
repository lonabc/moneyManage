package com.example.yinfuxi.Expection;

public class Myerror extends RuntimeException {
    public Myerror()
    {

    }
    public Myerror(String str)
    {
        super(str);
    }
}
