package com.example.yinfuxi.ProjectTest.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
@Slf4j
public class MyTask {//定时任务类
//    @Scheduled(cron = "0/5 * * * * ?")//每5秒触发一次
//    public void testTaks()
//    {
//        log.info("定时任务开始执行："+ LocalDateTime.now());
//    }

    @Scheduled(cron = "* 0/1 * ? * ? ")//每分钟触发一次
    public void processTimeouter()
    {
        log.info("每分钟触发一次：获取当前用户id");
    }
}
