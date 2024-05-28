package com.example.yinfuxi.Service;

import com.example.yinfuxi.StaticMy.Emp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> implements Serializable {
    private  boolean corect;
    private  String jwt;
    String loginResult;
    private Object data;
    private T testData;
    public Result(Object data)
    {
        this.data=data;
    }

    public Result(boolean corect,String jwt,Object data)
    {
        this.corect=corect;
        this.jwt=jwt;
        this.data=data;
    }
    public static Result success(String jwtKey)
    {
       Result result=new Result();
       result.jwt=jwtKey;
       return result;
    }

    public static Result error(String str)
    {
        Result result=new Result();
        result.loginResult=str;
        return result;
    }

}
