package com.lizy.helper.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.admin.entity.UserRole;
import com.lizy.helper.modules.app.model.PasswordModel;
import com.lizy.helper.modules.app.model.UserModel;

/**
 * @author lizy
 * @date 2021/8/27 下午2:19
 */
public interface IUserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userModel
     */
    void register(UserModel userModel);

}
