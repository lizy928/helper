package com.lizy.helper.modules.app.service.impl;

import com.lizy.helper.modules.app.entity.ChatRecord;
import com.lizy.helper.modules.app.service.IChatMessageService;
import com.lizy.helper.modules.app.service.IChatRecordService;
import com.lizy.helper.modules.common.dto.message.ChatMessage;
import com.lizy.helper.utils.UserTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author lzy
 * @date 2021/8/29
 */
@Service
public class ChatMessageService implements IChatMessageService {

    @Resource
    private IChatRecordService chatRecordService;

    @Override
    public void handleMessage(ChatMessage chatMessage) {
        final Integer id = UserTokenUtil.getUser().getId();

        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setFrom(Long.valueOf(id));
        chatRecord.setTo(1L);
        chatRecord.setType(chatMessage.getMsg().getType());
        chatRecord.setText(chatMessage.getMsg().getContent().getText());
        chatRecord.setUrl(chatMessage.getMsg().getContent().getUrl());
        chatRecord.setW(chatMessage.getMsg().getContent().getW());
        chatRecord.setH(chatMessage.getMsg().getContent().getH());
        chatRecord.setLength(chatMessage.getMsg().getContent().getLength());

        chatRecord.setCreateTime(new Date());
    }
}
