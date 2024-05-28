package com.example.yinfuxi.Control;

import com.example.yinfuxi.Service.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@ServletComponentScan//Filter拦截器相关注释，开启Service组件支持
@Controller
public class ControllerRes {

    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/testGetRes")
    public Result testRestemp() throws JsonProcessingException {
        // Gson gson=new Gson();
        //Get类型
        //类型一、普通访问，参数访问url和接收数据类型
        Result result= restTemplate.getForObject("http://127.0.0.1:8080/test", Result.class);
        //类型二、getForEntity获取
        ResponseEntity<Result> responseEntity=restTemplate.getForEntity("http://127.0.0.1:8080/test",Result.class);
        //获取状态对象
        HttpStatusCode httpStatus=responseEntity.getStatusCode();
        int statudCodeValue=httpStatus.value();
        System.out.println("获取状态码"+statudCodeValue);
        HttpHeaders httpHeaders=responseEntity.getHeaders();
        System.out.println("获取请求头"+httpHeaders);
        Result result1=responseEntity.getBody();
        System.out.println("获取请求体"+result1);
        //类型三、映射参数,利用Map
        Map<String,String> map=new HashMap<>();
        map.put("name","茶壶");
        restTemplate.getForEntity("http://127.0.0.1:8080/delete?name={name}",String.class,map);
        return result;
    }
    @PostMapping("/testPostRes")
    public Result testRestPost()
    {
        Map<String,String>param=new HashMap<>();
        param.put("name","殷传国");
        String name="殷传国";
        //post类型
        //普通传参 方式一，没有请求体null，这里的请求体可以是个对象，但是不常用这种方式传递请求体
        //Result result=restTemplate.postForObject("http://127.0.0.1:8080/list?name={name}",null,Result.class,name);
        //普通传参 方式二,利用map进行参数映射，没有请求体
        // Result result1=restTemplate.postForObject("http://127.0.0.1:8080/list?name={name}",null,Result.class,param);

        //传递参数，带请求体
        //设置请求头
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //包含jwt令牌的请求头
        headers.add("token","eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjI1ODkxM0FiYyIsIm5hbWUiOiLmrrfkvKDlm70iLCJpZCI6MywiZXhwIjoxNzEzMDM1MjI5fQ.Sl2jFSbqtY6RUBKq0vpIei7Ew3Oj2vpAAt108_jC1Z8");
        HttpEntity<String> entity=new HttpEntity<>(name,headers);//将要传递的参数和请求头
        ResponseEntity<Result> responseEntity=restTemplate.postForEntity("http://127.0.0.1:8080/list?name={name}",entity,Result.class,name);
        Result result1=responseEntity.getBody();
        System.out.println(result1);
        return result1;
    }
}
