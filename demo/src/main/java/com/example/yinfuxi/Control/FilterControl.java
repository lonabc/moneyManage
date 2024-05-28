package com.example.yinfuxi.Control;

import com.alibaba.fastjson.JSONObject;
import com.example.yinfuxi.Service.Result;
import com.example.yinfuxi.StaticMy.JwtMy;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static io.jsonwebtoken.lang.Strings.hasLength;

@Slf4j
//@WebFilter(urlPatterns = "/*") //拦截所有请求
public class FilterControl implements Filter //过滤器
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {//初始化方法，web服务器启动，创建Filter时调用。只调用一次
        System.out.println("Filter拦截器已经启动");
        Filter.super.init(filterConfig);
    }

    //拦截到请求时调用doFilter方法
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        String url=request.getRequestURI();
        log.info("当前请求的url为"+url);
        System.out.println("已有请求被拦截");
        if(url.contains("login"))//判断当前的url中时是否包含login，因为login是不需要被拦截的
        {
          log.info("登录操作，放行...");
          filterChain.doFilter(servletRequest,servletResponse);//放行，继续执行Control里的方法
           return;
        }
        String jwt=request.getHeader("token");//token是自己给请求头定义的名字，此处主要是根据token获取请求头
        if(!StringUtils.hasLength(jwt))
        {
            log.info("未登入用户");
            Result error= Result.error("NOT_LOGIN");
            //该类非接收类没有@RequestController注解，需要手动将转换JSON
            String notLogin= JSONObject.toJSONString(error);
            servletResponse.getWriter().write(notLogin);//getWriter()获取输入流，write将其直接发给前端
            return;
        }
        try{
            JwtMy.findJwt(jwt);
        }catch(Exception e)
        {
            e.printStackTrace();//将错误输出到控制台
            Result error=Result.error("NOT_LOGIN");
            String notLogin=JSONObject.toJSONString(error);
            servletResponse.getWriter().write(notLogin);
            return;
        }
        log.info("登入成功");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {//当后端服务器关闭时会触发该方法
        log.info("Filter过滤器已被销毁");
        Filter.super.destroy();
    }
}