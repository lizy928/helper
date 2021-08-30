package com.lizy.helper.modules.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lizy.helper.modules.app.entity.ChatRecord;
import com.lizy.helper.modules.app.mapper.ChatRecordMapper;
import com.lizy.helper.modules.app.service.IChatRecordService;
import org.springframework.stereotype.Service;

/**
 * @author lzy
 * @date 2021/8/29
 */
@Service
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordMapper, ChatRecord> implements IChatRecordService {
}
