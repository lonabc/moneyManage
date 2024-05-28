package com.example.yinfuxi.configer;

import com.example.yinfuxi.Control.InterceptorController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.ApiInfo;

import java.time.LocalDate;
//@Configurable
@Configuration//表示该类配置类
@Slf4j
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private InterceptorController interceptTest;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(interceptTest).addPathPatterns("/**").excludePathPatterns("/testCode")
                 .excludePathPatterns("/testRes") .excludePathPatterns("/register").excludePathPatterns("/loginV2");

//        registry.addInterceptor(interceptTest).excludePathPatterns("/**");


        //addPathPatterns表示需要拦截哪些方法，excludePathPattern不需要拦截那些方法
    }


}
