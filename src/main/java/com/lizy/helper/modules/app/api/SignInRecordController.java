package com.lizy.helper.modules.app.api;

import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.app.model.SignInDetailModel;
import com.lizy.helper.modules.app.model.SignInRecordModel;
import com.lizy.helper.modules.app.service.ISignInRecordService;
import com.lizy.helper.modules.common.annotation.LoginUser;
import com.lizy.helper.modules.common.dto.output.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * 打卡记录
 *
 * @author lzy
 * @date 2021/8/28
 */
@RequestMapping("/app/signInRecord")
@RestController
public class SignInRecordController {

    @Resource
    private ISignInRecordService signInRecordService;

    /**
     * 打卡
     *
     * @param user              当前登录对象
     * @param signInRecordModel 打卡信息
     * @return
     */
    @PostMapping
    public Object signIn(@LoginUser User user, @RequestBody SignInRecordModel signInRecordModel) {
        //判断是否已经签到
        if (!CollectionUtils.isEmpty(signInRecordService.listByDate(signInRecordModel.getSignInId(),
                signInRecordModel.getSignInTime(), signInRecordModel.getSignInTime()))) {
            return ApiResult.fail("已签到！");
        }
        signInRecordService.signIn(signInRecordModel);
        return ApiResult.ok("ok");
    }

    /**
     * 记录查询
     *
     * @param user 当前登录对象
     * @param date  月份 2021-9
     * @return
     */
    @GetMapping("/{signId}")
    public Object list(@LoginUser User user, @PathVariable Long signId, @RequestParam String date) throws ParseException {
        final String[] split = date.split("-");
        if(StringUtils.isBlank(date) || split.length < 2 ){
            return ApiResult.ok("参数异常！");
        }
        SignInDetailModel signInDetailModel = signInRecordService.listDetail(signId, split);
        return ApiResult.ok("ok", signInDetailModel);
    }
}
