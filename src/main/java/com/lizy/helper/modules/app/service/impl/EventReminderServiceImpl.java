package com.lizy.helper.modules.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lizy.helper.modules.app.entity.EventReminder;
import com.lizy.helper.modules.app.mapper.EventReminderMapper;
import com.lizy.helper.modules.app.service.IEventReminderService;
import org.springframework.stereotype.Service;

/**
 * @author lzy
 * @date 2021/8/29
 */
@Service
public class EventReminderServiceImpl extends ServiceImpl<EventReminderMapper, EventReminder> implements IEventReminderService {
}
