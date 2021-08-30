package com.lizy.helper.modules.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lizy.helper.modules.app.entity.SignIn;
import com.lizy.helper.modules.app.mapper.SignInMapper;
import com.lizy.helper.modules.app.service.ISignInService;
import org.springframework.stereotype.Service;

/**
 * @author lzy
 * @date 2021/8/28
 */
@Service
public class SignInServiceImpl extends ServiceImpl<SignInMapper, SignIn> implements ISignInService {
}
