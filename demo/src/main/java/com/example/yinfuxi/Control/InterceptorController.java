package com.example.yinfuxi.Control;

import com.alibaba.fastjson.JSONObject;
import com.example.yinfuxi.Service.Result;
import com.example.yinfuxi.StaticMy.JwtMy;
import com.example.yinfuxi.StaticMy.ToolsConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@Component //交給ioc容器管理
public class InterceptorController implements HandlerInterceptor {


    @Autowired
    private RedisTemplate redisTemplate;

    //拦截请求，执行资源方法前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、获取url
        log.info("已拦截请求");
        String strUrl=request.getRequestURI().toString();
        log.info("请求的url为"+strUrl);
        //2、判断请求url中是否包含login,如果包包含就放行，其实这一步可以省略在注册拦截器的代码中调用方法不拦截login就行
        if(strUrl.contains("error"))
        {
            log.info("请求出错");
            return false;
        }

        if(strUrl.contains("login") || strUrl.contains("Code"))
        {
            return true;
        }

        //3、获取请求头中的令牌（token）
        String token=request.getHeader("token");//在强调一遍token是自己定义的请求·头的名字

        //4、判断令牌是否存在，如果不存在，返回错误结果
        if(!StringUtils.hasLength(token)) {
            log.info("jwt令牌为空请重新登录");
            Result error =Result.error("NOT_LOGIN");
            String nolog= JSONObject.toJSONString(error);
            response.getWriter().write(nolog);
            return false;
        }
        System.out.println(token);



        //解析token
        try{
            JwtMy.findJwt(token);
        }catch(Exception e)
        {
            e.printStackTrace();
            log.info("解析令牌失败,请重新登录");
            Result error =Result.error("NOT_LOGIN");
            String nolog= JSONObject.toJSONString(error);
            response.getWriter().write(nolog);
            return false;
        }

        log.info("登录成功");
        return true;

    }

    //执行资源方法后，执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("请求拦截后");
    }

    //视图渲染完毕后执行，最后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
