package com.example.yinfuxi.UserTools;

import com.example.yinfuxi.StaticMy.ToolsConst;
import com.example.yinfuxi.configer.BaseContextMy;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class Tools {


    public static String charSet="0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";

    public static SimpleMailMessage generateRandomCode(int length,RedisTemplate redisTemplate,String email)
    {
        SimpleMailMessage simpMsg=new SimpleMailMessage();
        simpMsg.setFrom("3401531269@qq.com");
        simpMsg.setTo(email);
        simpMsg.setSubject("消费管理系统登录验证码，有效时间60s，请尽快输入");
        StringBuilder code=new StringBuilder();
        Random random=new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex=random.nextInt(charSet.length());
            code.append(charSet.charAt(randomIndex));
        }
        String identifyCode=code.toString();
        redisTemplate.opsForValue().set("code",identifyCode,180, TimeUnit.SECONDS);
        simpMsg.setText(identifyCode);
        ToolsConst.code=identifyCode; //存储使用静态变量存储
        BaseContextMy.setstring(identifyCode); //使用线程进行存储
        System.out.println("获取验证码"+redisTemplate.opsForValue().get("code"));
        return simpMsg;
    }
}
