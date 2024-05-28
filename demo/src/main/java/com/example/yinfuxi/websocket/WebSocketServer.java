package com.example.yinfuxi.websocket;

import com.example.yinfuxi.Service.ServiceMy;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//@Component
//@ServerEndpoint("/ws/{sid}")//TODO 与本项目无关，该注解表示此类是一个websocket端点，通过value注解，指定websocket的路径
public class WebSocketServer {//webSocketServer测试类
    //存放会话对象

    private  static ServiceMy serviceMy;
   // @Autowired
    public void setServiceMy(ServiceMy ServiceMy) {
        WebSocketServer.serviceMy = ServiceMy;
    }

    private static Map<String, Session> sessionMap=new HashMap<>();
    @OnOpen //表示该方法为回调方法，表示建立连接时会调用这个方法
    public void onOpen(Session session, @PathParam("sid") String sid)//session 表示客户端和服务端建立起来的会话，通过@PathParam对sid的识别进行客户端的绑定
    {
        System.out.println(WebSocketServer.serviceMy.selectAll());
        System.out.println("回调方法：客户端"+sid+"建立连接");
        sessionMap.put(sid,session);
    }

    @OnMessage //表示该方法为回调方法，收到客户端消息后调用的方法
    public void onMessage(String message,@PathParam("sid") String sid)//message客户端发送的消息
    {
        System.out.println("收到来自客户端："+sid+"的消息"+message);
    }

    @OnClose//连接关闭调用的回调方法
    public void onClost(@PathParam("sid") String sid)
    {
        System.out.println("连接断开"+sid);
        sessionMap.remove(sid);
    }
    //向所有会话发消息
    public void sendToAllClient(String message)
    {
        Collection<Session> sessions=sessionMap.values();
        for(Session session:sessions)
        {
            try{
                //服务端向客户端发送消息
                session.getBasicRemote().sendText(message);
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
