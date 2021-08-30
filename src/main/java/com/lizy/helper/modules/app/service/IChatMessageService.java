package com.lizy.helper.modules.app.service;

import com.lizy.helper.modules.common.dto.message.ChatMessage;

/**
 * @author lzy
 * @date 2021/8/29
 */
public interface IChatMessageService {

    /**
     * 处理消息
     *
     * @param chatMessage
     */
    public void handleMessage(ChatMessage chatMessage);
}
