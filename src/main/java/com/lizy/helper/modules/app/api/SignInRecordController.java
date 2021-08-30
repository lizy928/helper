package com.lizy.helper.modules.app.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.app.entity.SignInRecord;
import com.lizy.helper.modules.app.model.SignInRecordModel;
import com.lizy.helper.modules.app.service.ISignInRecordService;
import com.lizy.helper.modules.common.annotation.LoginUser;
import com.lizy.helper.modules.common.dto.output.ApiResult;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
     * @return
     */
    @GetMapping("/{signId}")
    public Object list(@LoginUser User user, @PathVariable Long signId) {
        List<SignInRecord> recordList = signInRecordService.selectList(new EntityWrapper<SignInRecord>()
                .eq("sign_in_id", signId)
        );
        List<SignInRecordModel> modelList = recordList.stream().map(signInRecord -> {
            SignInRecordModel signInRecordModel = new SignInRecordModel();
            BeanUtils.copyProperties(signInRecord, signInRecordModel);
            return signInRecordModel;
        }).collect(Collectors.toList());

        return ApiResult.ok("ok", modelList);
    }
}
