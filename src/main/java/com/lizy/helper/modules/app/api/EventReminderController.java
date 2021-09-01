package com.lizy.helper.modules.app.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.app.entity.EventReminder;
import com.lizy.helper.modules.app.model.EventReminderModel;
import com.lizy.helper.modules.app.service.IEventReminderService;
import com.lizy.helper.modules.common.annotation.LoginUser;
import com.lizy.helper.modules.common.dto.output.ApiResult;
import com.lizy.helper.utils.LunarSolarConverterUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 时间提醒
 *
 * @author lzy
 * @date 2021/8/29
 */
@RequestMapping("/app/eventReminder")
@RestController
public class EventReminderController {

    @Resource
    private IEventReminderService eventReminderService;

    /**
     * 新增提醒
     *
     * @param user          当前登录对象
     * @param eventReminder 提醒事件
     * @return
     */
    @PostMapping
    public Object add(@LoginUser User user, @RequestBody EventReminder eventReminder) {
        eventReminder.setUserId(Long.valueOf(user.getId()));
        eventReminderService.insert(eventReminder);
        return ApiResult.ok("ok");
    }

    /**
     * 新增事件提醒
     *
     * @param user 当前登录对象
     * @return
     */
    @GetMapping
    public Object list(@LoginUser User user) {
        final List<EventReminder> reminderList = eventReminderService.selectList(new EntityWrapper<EventReminder>()
                .eq("user_id", user.getId())
        );

        List<EventReminderModel> modelList = reminderList.stream().map(item -> {
            EventReminderModel eventReminderModel;
            if(item.getTimeType() == 0){
                // 获取有多少时间
                eventReminderModel = LunarSolarConverterUtil.getTimeDiff(item.getTime());
            } else {
                //转阳历，计算
                final Date date = LunarSolarConverterUtil.lunarToDate(item.getTime());
                eventReminderModel = LunarSolarConverterUtil.getTimeDiff(date);
            }
            eventReminderModel.setId(item.getId());
            eventReminderModel.setName(item.getName());
            eventReminderModel.setCreateTime(item.getCreateTime());
            eventReminderModel.setUpdateTime(item.getUpdateTime());
            return eventReminderModel;
        }).collect(Collectors.toList());

        return ApiResult.ok("ok", modelList);
    }

    /**
     * 更新事件提醒
     *
     * @param user          当前登录对象
     * @param eventReminder 提醒事件
     * @param id            主键Id
     * @return
     */
    @PutMapping("/{id}")
    public Object update(@LoginUser User user, @RequestBody EventReminder eventReminder, @PathVariable Long id) {
        eventReminder.setId(id);
        eventReminderService.updateById(eventReminder);
        return ApiResult.ok("ok");
    }

    /**
     * 删除事件提醒
     *
     * @param id 主键Id
     * @return
     */
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Long id) {
        eventReminderService.deleteById(id);
        return ApiResult.ok("ok");
    }
}
