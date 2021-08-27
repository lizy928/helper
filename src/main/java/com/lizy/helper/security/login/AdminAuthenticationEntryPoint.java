package com.lizy.helper.security.login;

import com.lizy.helper.modules.common.dto.output.ApiResult;
import com.lizy.helper.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
@Slf4j
@Component
public class AdminAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        // 未登录 或 token过期
        if (e!=null){
            ResponseUtils.out(response, ApiResult.expired(e.getMessage()));
        } else {
            ResponseUtils.out(response, ApiResult.expired("jwtToken过期!"));
        }
    }

}
