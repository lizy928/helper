package com.lizy.helper.modules.app.service;

import com.lizy.helper.modules.app.model.PasswordModel;
import com.lizy.helper.modules.app.model.UserModel;

/**
 * @author lizy
 * @date 2021/8/27 下午2:19
 */
public interface IUserService {

    /**
     * 用户注册
     *
     * @param userModel
     */
    void register(UserModel userModel);

    void updatePassword(PasswordModel passwordModel);
}