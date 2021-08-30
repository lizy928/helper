package com.lizy.helper.modules.app.api;

import com.alibaba.fastjson.JSON;
import com.lizy.helper.modules.app.service.IRobotService;
import com.lizy.helper.modules.common.dto.message.ChatMessage;
import com.lizy.helper.utils.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lzy
 * @date 2021/8/29
 */
@Slf4j
@ServerEndpoint(value = "/app/im/robot")
@Component
public class ChatWebSocket {

    /**
     * 记录当前在线连接数
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * 存放所有在线的客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>(500);

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        onlineCount.incrementAndGet(); // 在线数加1
        clients.put(session.getId(), session);
        log.info("有新连接加入：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        onlineCount.decrementAndGet(); // 在线数减1
        clients.remove(session.getId());
        log.info("有一连接关闭：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("服务端收到客户端[{}]的消息[{}]", session.getId(), message);
        try {
            ChatMessage chatMessage = JSON.parseObject(message, ChatMessage.class);
            if(chatMessage.getType().equals("heartBeat")){
                log.info("websocket 心跳检测.......");
                return;
            }
            if (chatMessage != null) {
                Session toSession = clients.get(session.getId());
                if (toSession != null) {
                    // 多线程处理
                    final IRobotService robotService = ApplicationContextUtil.getApplicationContext().getBean(IRobotService.class);
                    final String robotMessage = robotService.getMessage(chatMessage.getMsg().getContent().getText());
                    chatMessage.getMsg().getContent().setText(robotMessage);
                    this.sendMessage(JSON.toJSONString(chatMessage), toSession);
                    // heandle message
                }
            }
        } catch (Exception e) {
            log.error("解析失败：{}", e);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息[{}]", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败：{}", e);
        }
    }
}
