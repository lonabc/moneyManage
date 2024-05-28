package com.example.yinfuxi.StaticMy;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
//@RestControllerAdvice
public class CustomExceptionHandler {//全局异常处理器
    @ExceptionHandler(RuntimeException.class)
    public void abrouseException(RuntimeException r)
    {
        r.printStackTrace();
        log.info("运行时间出现，异常或者未序列化");
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public void uploadException(MaxUploadSizeExceededException maxe, HttpServletResponse response)throws IOException
    {
       response.setContentType("text/html;charset=utf-8");//服务端将内容以utf-8的内容发送，并要求浏览器以utf-8的格式显示
        PrintWriter out= response.getWriter();
        out.write("上传文件超出限制");
        out.flush();
        out.close();
    }
}

