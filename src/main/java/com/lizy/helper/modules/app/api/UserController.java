package com.lizy.helper.modules.app.api;

import com.lizy.helper.modules.app.model.PasswordModel;
import com.lizy.helper.modules.app.model.UserModel;
import com.lizy.helper.modules.app.service.IUserService;
import com.lizy.helper.modules.common.dto.output.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
     * @param userModel
     * @return
     */
    @PostMapping("")
    public Object register(@RequestBody UserModel userModel){
        userService.register(userModel);
        return ApiResult.ok("注册成功!");
    }

    @PutMapping("/password")
    public Object updatePassword(@RequestBody PasswordModel passwordModel){
        userService.updatePassword(passwordModel);
        return ApiResult.ok("修改成功!");
    }

}