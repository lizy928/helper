package com.lizy.helper.modules.app.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.app.entity.SignIn;
import com.lizy.helper.modules.app.entity.SignInRecord;
import com.lizy.helper.modules.app.model.SignInModel;
import com.lizy.helper.modules.app.service.ISignInRecordService;
import com.lizy.helper.modules.app.service.ISignInService;
import com.lizy.helper.modules.common.annotation.LoginUser;
import com.lizy.helper.modules.common.dto.output.ApiResult;
import com.lizy.helper.utils.DateTimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 打卡项目
 *
 * @author lzy
 * @date 2021/8/28
 */
@RequestMapping("/app/signIn")
@RestController
public class SignInController {

    @Resource
    private ISignInService signInService;
    @Autowired
    private ISignInRecordService signInRecordService;

    /**
     * 新增打卡项目
     *
     * @param signInModel 打卡对象
     * @return
     */
    @PostMapping("")
    public Object add(@LoginUser User user, @RequestBody SignInModel signInModel) {
        SignIn signIn = new SignIn();
        signIn.setName(signInModel.getName());
        signIn.setDesc(signInModel.getDesc());
        signIn.setUserId(Long.valueOf(user.getId()));
        signInService.insert(signIn);
        return ApiResult.ok("创建成功！");
    }

    /**
     * 项目列表
     *
     * @param user 当前登录用户
     * @return
     */
    @GetMapping
    public Object list(@LoginUser User user) {
        final List<SignIn> signInList = signInService.selectList(new EntityWrapper<SignIn>().eq("user_id", user.getId()));
        signInList.forEach(signIn -> {
            final List<SignInRecord> signInRecordList = signInRecordService.listByDate(signIn.getId(), DateTimeUtils.getStartToday(), DateTimeUtils.getEndToday());
            signIn.setTodaySignIn(!CollectionUtils.isEmpty(signInRecordList));
        });
        return ApiResult.ok("ok", signInList);
    }

    /**
     * 查询项目
     *
     * @param user 当前登录用户
     * @return
     */
    @GetMapping("/{id}")
    public Object get(@LoginUser User user, @PathVariable Long id) {
        final SignIn signIn = signInService.selectById(id);
        return ApiResult.ok("ok", signIn);
    }

    /**
     * 更新打卡项目
     *
     * @param signInModel 打卡对象
     * @return
     */
    @PutMapping("/{id}")
    public Object update(@LoginUser User user, @RequestBody SignInModel signInModel, @PathVariable Long id) {
        SignIn signIn = new SignIn();
        signIn.setId(id);
        BeanUtils.copyProperties(signInModel, signIn);
        signInService.updateById(signIn);
        return ApiResult.ok("ok");
    }

    /**
     * 删除项目
     *
     * @param user 当前登录用户
     * @return
     */
    @DeleteMapping("/{id}")
    public Object delete(@LoginUser User user, @PathVariable Long id) {
        signInService.deleteById(id);
        signInRecordService.delete(new EntityWrapper<SignInRecord>().eq("sign_id_id", id));
        return ApiResult.ok("ok");
    }

}
