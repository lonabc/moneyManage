package com.example.yinfuxi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling //启用
@EnableCaching //启用Spring Cache
@SpringBootApplication
@MapperScan("com.example.yinfuxi.Mapper")//扫描该包下的所有接口作为Mapper
public class YinfuxiApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(YinfuxiApplication.class, args);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
