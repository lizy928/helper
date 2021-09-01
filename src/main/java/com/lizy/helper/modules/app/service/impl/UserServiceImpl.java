package com.lizy.helper.modules.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.admin.mapper.UserMapper;
import com.lizy.helper.modules.app.model.PasswordModel;
import com.lizy.helper.modules.app.model.UserModel;
import com.lizy.helper.modules.app.service.IUserService;
import com.lizy.helper.modules.common.constans.Constants;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService  {

    @Resource
    private UserMapper userMapper;

    @Override
    public void register(UserModel userModel) {
        User user = new User();
        user.setUsername(userModel.getUsername());
        final String salt = CommonUtil.generateSalt();
        user.setPassword(PasswordUtils.encodePassword(userModel.getPassword(), salt));
        user.setNickName(userModel.getNickName());
        user.setSalt(salt);
        user.setFlag("1");
        user.setType(Constants.USER_TYPE_COMMON);
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        userMapper.insert(user);
    }

}
