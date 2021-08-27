package com.lizy.helper.utils;

import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.admin.mapper.UserMapper;
import com.lizy.helper.modules.common.constans.Constants;

/**
 * @author lizy
 * @date 2021/8/27 下午2:40
 */
public class UserTokenUtil {

    public static User getUser(){
        final String token = ApplicationContextUtil.getRequest().getHeader(Constants.REQUEST_HEADER);
        final UserMapper userMapper = ApplicationContextUtil.getApplicationContext().getBean(UserMapper.class);
        return userMapper.getUserInfoByToken(token);
    }
}