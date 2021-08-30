package com.lizy.helper.modules.app.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.app.model.PasswordModel;
import com.lizy.helper.modules.app.model.UserModel;
import com.lizy.helper.modules.app.service.IUserService;
import com.lizy.helper.modules.common.dto.output.ApiResult;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 下午2:18
 */
@RequestMapping("/app/user")
@RestController
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 用户注册
     *
     * @param userModel 登录对象
     * @return
     */
    @PostMapping("/register")
    public Object register(@RequestBody UserModel userModel) {
        final List<User> userList = userService.selectList(new EntityWrapper<User>().eq("username", userModel.getUsername()));
        if (!CollectionUtils.isEmpty(userList)) {
            return ApiResult.fail("手机号已被注册！");
        }
        userService.register(userModel);
        return ApiResult.ok("注册成功!");
    }

    /**
     * 修改密码
     *
     * @param passwordModel 密码对象
     * @return
     */
    @PutMapping("/password")
    public Object updatePassword(@RequestBody PasswordModel passwordModel) {
        userService.updatePassword(passwordModel);
        return ApiResult.ok("修改成功!");
    }

}
