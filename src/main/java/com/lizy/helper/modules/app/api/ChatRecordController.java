package com.lizy.helper.modules.app.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.app.entity.ChatRecord;
import com.lizy.helper.modules.app.service.IChatRecordService;
import com.lizy.helper.modules.common.annotation.LoginUser;
import com.lizy.helper.modules.common.dto.output.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzy
 * @date 2021/8/29
 */
@RequestMapping("/app/chatRecord")
@RestController
public class ChatRecordController {

    @Resource
    private IChatRecordService chatRecordService;

    @GetMapping
    public Object list(@LoginUser User user, Long to) {
        final List<ChatRecord> recordList = chatRecordService.selectList(new EntityWrapper<ChatRecord>()
                .eq("from", user.getId())
        );
        return ApiResult.ok("ok", recordList);
    }

}
