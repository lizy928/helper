package com.lizy.helper.modules.app.service.impl;

import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.admin.mapper.UserMapper;
import com.lizy.helper.modules.app.model.PasswordModel;
import com.lizy.helper.modules.app.model.UserModel;
import com.lizy.helper.modules.app.service.IUserService;
import com.lizy.helper.utils.CommonUtil;
import com.lizy.helper.utils.PasswordUtils;
import com.lizy.helper.utils.UserTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author lizy
 * @date 2021/8/27 下午2:19
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void register(UserModel userModel) {
        User user = new User();
        user.setUsername(userModel.getUsername());
        user.setPassword(PasswordUtils.encodePassword(userModel.getPassword(), CommonUtil.generateSalt()));
        user.setNickName(userModel.getNickName());
        user.setFlag("1");
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        userMapper.insert(user);
    }

    @Override
    public void updatePassword(PasswordModel passwordModel) {
        final User user = UserTokenUtil.getUser();
        if(user.getPassword().equals(PasswordUtils.encodePassword(passwordModel.getPassword(), user.getSalt()))){
            user.setPassword(PasswordUtils.encodePassword(passwordModel.getNewPassword(), user.getSalt()));
            user.setGmtModified(new Date());
            userMapper.updateById(user);
        }
    }
}