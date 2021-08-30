package com.lizy.helper.modules.common.handle;

import com.lizy.helper.modules.admin.dto.model.UserInfoVO;
import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.admin.service.ISysUserService;
import com.lizy.helper.modules.common.annotation.LoginUser;
import com.lizy.helper.modules.common.constans.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author lzy
 * @date 2021/8/28
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private ISysUserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        final String token = nativeWebRequest.getHeader(Constants.REQUEST_HEADER);
        final UserInfoVO userInfoVO = userService.getCurrentUserInfo(token);
        User user = new User();
        BeanUtils.copyProperties(userInfoVO, user);
        return user;
    }

}
