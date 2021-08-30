package com.lizy.helper.modules.common.dto.message;

import lombok.Data;

/**
 * 聊天消息对象
 *
 * @author lzy
 * @date 2021/8/29
 */
@Data
public class ChatMessage {

    private String type;

    private Msg msg;
}



