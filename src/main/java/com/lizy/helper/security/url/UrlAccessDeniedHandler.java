package com.lizy.helper.security.url;

import com.lizy.helper.modules.common.dto.output.ApiResult;
import com.lizy.helper.modules.common.enumeration.ResultCode;
import com.lizy.helper.utils.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
@Component
public class UrlAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtils.out(response, ApiResult.fail(ResultCode.UNAUTHORIZED.getCode(), e.getMessage()));
    }
}
