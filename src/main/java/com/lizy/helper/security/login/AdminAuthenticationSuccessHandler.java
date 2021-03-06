package com.lizy.helper.security.login;

import com.lizy.helper.modules.common.dto.output.ApiResult;
import com.lizy.helper.security.dto.SecurityUser;
import com.lizy.helper.utils.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lizy
 * @date 2021/8/27 10:35
 */
@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        SecurityUser securityUser = ((SecurityUser) auth.getPrincipal());
        ResponseUtils.out(response, ApiResult.ok("登录成功!", securityUser.getCurrentUserInfo()));
    }
}
