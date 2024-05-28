package com.example.yinfuxi.ProjectTest.task;

import com.example.yinfuxi.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@Component
public class WebSocketTask {//TODO 该类仅为测试WebSocket使用
   // @Autowired
    private WebSocketServer webSocketServer;//注入

    @Scheduled(cron = "0/5 * * * * ?")
    public void sendMessageToClient()//每隔五秒群发消息
    {
        webSocketServer.sendToAllClient("这是来自服务端的消息："+ DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
    }
}
