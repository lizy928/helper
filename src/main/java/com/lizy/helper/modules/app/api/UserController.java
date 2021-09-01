package com.lizy.helper.modules.app.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.app.model.PasswordModel;
import com.lizy.helper.modules.app.model.UserModel;
import com.lizy.helper.modules.app.service.IUserService;
import com.lizy.helper.modules.common.annotation.LoginUser;
import com.lizy.helper.modules.common.dto.output.ApiResult;
import com.lizy.helper.utils.PasswordUtils;
import com.lizy.helper.utils.UserTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        User user = userService.selectOne(new EntityWrapper<User>().eq("username",  passwordModel.getUsername()));
        if(Objects.isNull(user)){
            return ApiResult.fail("用户不存在！");
        }
        if(user.getPassword().equals(PasswordUtils.encodePassword(passwordModel.getPassword(), user.getSalt()))){
            User userTem = new User();
            userTem.setId(user.getId());
            userTem.setPassword(PasswordUtils.encodePassword(passwordModel.getNewPassword(), user.getSalt()));
            userTem.setGmtModified(new Date());
            userService.updateById(userTem);
        } else {
            return ApiResult.fail("旧密码错误！");
        }
        return ApiResult.ok("修改成功!");
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable Long id, @LoginUser User user) {
        return ApiResult.ok("ok", userService.selectById(user.getId()));
    }

    /**
     * 修改用户资料
     *
     * @param user      当前对象
     * @param userModel model
     * @return
     */
    @PutMapping()
    public Object update(@LoginUser User user, @RequestBody UserModel userModel) {
        User userTem = new User();
        userTem.setId(user.getId());
        BeanUtils.copyProperties(userModel, userTem);
        userService.updateById(userTem);
        return ApiResult.ok("ok", userService.selectById(user.getId()));
    }
}
