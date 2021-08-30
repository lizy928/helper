package com.lizy.helper.security.url;

import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.common.constans.Constants;
import com.lizy.helper.security.dto.SecurityUser;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;


/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    /**
     * @param authentication: 当前登录用户的角色信息
     * @param object:         请求url信息
     * @param collection:     `UrlFilterInvocationSecurityMetadataSource`中的getAttributes方法传来的，表示当前请求需要的角色（可能有多个）
     * @return: void
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, AuthenticationException {
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new BadCredentialsException("未登录!");
        }
        //普通用户放行，管理员需要校验角色权限
        final User userInfo = ((SecurityUser) authentication.getPrincipal()).getCurrentUserInfo();
        if (Objects.equals(Constants.USER_TYPE_COMMON, userInfo.getType())) {
            return;
        }
        // 遍历角色
        for (ConfigAttribute ca : collection) {
            // ① 当前url请求需要的权限
            String needRole = ca.getAttribute();
            if (Constants.ROLE_LOGIN.equals(needRole)) {
                throw new AccessDeniedException("未授权该url！");
            }

            // ② 当前用户所具有的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                // 只要包含其中一个角色即可访问
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("请联系管理员分配权限！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
