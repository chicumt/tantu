package com.example.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.utils.BaseContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket服务
 */
@Component
@ServerEndpoint("/rtc/{sid}")
public class WebrtcServer {
    private static UserDao userDao;
    @Autowired
    private void setUserDao(UserDao userDao){
        WebrtcServer.userDao=userDao;
    }
    //存放会话对象
    private static Map<String, Session> sessionMap = new HashMap();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        System.out.println(userDao.queryById(1L));

        System.out.println(BaseContext.getCurrentId());
        System.out.println("客户端：" + sid + "建立连接");
        sendToAllClient("这是来自服务端的消息：" + DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
        sessionMap.put(sid, session);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) {
        // 将 JSON 字符串解析为 JSONObject
        JSONObject jsonObject = JSON.parseObject(message);

        // 从 JSONObject 中获取数据
//        String name = jsonObject.getString("name");
        String text = message;
        System.out.println("收到来自客户端：" + sid + "的信息:" + text);

        Session session = sessionMap.get(sid);
        if(session==null){
            System.out.println("当前用户已离线");
            return;
        }
        sendClient(text,session);
    }

    /**
     * 连接关闭调用的方法
     *
     * @param sid
     */
    @OnClose
    public void onClose(@PathParam("sid") String sid) {
        System.out.println("连接断开:" + sid);
        sessionMap.remove(sid);
    }
    public String getHeader(Session session, String headerName) {
        String header = (String) session.getUserProperties().get(headerName);
        System.out.println(header);
        if (StringUtils.isBlank(header)) {
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return header;
    }
    /**
     * 群发
     *
     * @param message
     */
    public void sendClient(String message,Session session1) {
        Collection<Session> sessions = sessionMap.values();
        for (Session session : sessions) {
            try {
                //服务器向客户端发送消息
                System.out.println(session);
                if(session1.equals(session)){
                    session.getBasicRemote().sendText(message);
                    System.out.println("成功发送消息");
                    break;
                };
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void sendToAllClient(String message) {
        Collection<Session> sessions = sessionMap.values();
        for (Session session : sessions) {
            try {
                //服务器向客户端发送消息
                System.out.println(session);

                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
