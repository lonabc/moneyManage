package com.example.yinfuxi.configer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //TODO 与本项目无关
public class MyWebMvcConfig implements WebMvcConfigurer {//设置全局CORS跨域配置

    @Override
    public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/book/**")
               .allowedHeaders("*")
               .allowedMethods("*")
               .maxAge(1800)
               .allowedOrigins("http://localhost:8081");
    }
}
