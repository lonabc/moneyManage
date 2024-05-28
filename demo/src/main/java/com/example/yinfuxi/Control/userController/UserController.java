package com.example.yinfuxi.Control.userController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.yinfuxi.Mapper.UserMapper;
import com.example.yinfuxi.Service.Result;
import com.example.yinfuxi.Service.ServiceMy;
import com.example.yinfuxi.Service.UserService.MoneyService;
import com.example.yinfuxi.Service.UserService.MoneyServiceImp;
import com.example.yinfuxi.StaticMy.User;
import com.example.yinfuxi.configer.BaseContextMy;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
@CrossOrigin("*")
@Tag(name="用户管理")
public class UserController {

    @Autowired
    MoneyService moneyService;
    @Autowired
    RestTemplate restTemplate;

   @PostMapping("/moneyRember")
   @Operation(summary = "记入消费")
    public void test(String form1)
   {
       User user= JSON.parseObject(form1,User.class);
       System.out.println(user);
       user.setId(BaseContextMy.get());
      // System.out.println(JSONArray.parseArray(JSON.toJSONString(user.getType()), String.class));
       List<String> list=JSONArray.parseArray(JSON.toJSONString(user.getClassify()),String.class);//注意，关于前端对象中含有数组的接收方式
       for (String s : list) {
           user.setClassify(s);
       }
       System.out.println(user.getClassify());
       moneyService.insertMoney(user);
//       HttpHeaders headers=new HttpHeaders();
//       headers.setContentType(MediaType.APPLICATION_JSON);//设置传输数据格式为JSON
//       headers.add("token",BaseContextMy.get2());
//       HttpEntity<String> entity=new HttpEntity<>(headers);

   }
   @GetMapping("/moneyAll")
   @Operation(summary = "获取消费记录")
    public Result selectAll()
   {
       Result result=new Result();
       System.out.println("Test");
        result.setData(moneyService.selectAll());
        System.out.println(result.getData().toString());
        return  result;
   }
   @GetMapping("/CostAll")
   @Operation(summary ="获取消费类和对应消费金额" )
    public Result selectCost()
   {

       System.out.println("Terst1");

       return moneyService.selectCost();
   }
   @GetMapping("/CostTime")
   @Operation(summary = "根据时间查询")
    public Result selectTime(String dayTime)
   {
       System.out.println("Test2");
       return moneyService.selectTime(dayTime);
   }
   @PostMapping("/moneyChange")
    public void updateCost(@RequestBody User user)
   {
       System.out.println(user);
   }



}
