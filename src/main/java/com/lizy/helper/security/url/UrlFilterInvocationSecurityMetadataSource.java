package com.lizy.helper.security.url;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lizy.helper.config.MyProperties;
import com.lizy.helper.modules.admin.entity.Menu;
import com.lizy.helper.modules.admin.entity.Role;
import com.lizy.helper.modules.admin.entity.RoleMenu;
import com.lizy.helper.modules.admin.mapper.MenuMapper;
import com.lizy.helper.modules.admin.mapper.RoleMapper;
import com.lizy.helper.modules.admin.mapper.RoleMenuMapper;
import com.lizy.helper.modules.common.constans.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuMapper menuMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    MyProperties myProperties;

    /***
     * 返回该url所需要的用户权限信息
     *
     * @param object: 储存请求url信息
     * @return: null：标识不需要任何权限都可以访问
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取当前请求url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // TODO 忽略url请放在此处进行过滤放行
        for (String ignoreUrl : myProperties.getAuth().getIgnoreUrls()) {
            if (ignoreUrl.equals(requestUrl)){
                return null;
            }
        }

        if (requestUrl.contains("/login") || requestUrl.contains("/groupChat")){
            return null;
        }

        // 数据库中所有url
        List<Menu> permissionList = menuMapper.selectList(null);
        for (Menu permission : permissionList) {
            // 获取该url所对应的权限
            if (("/api" + permission.getUrl()).equals(requestUrl)) {
                List<RoleMenu> permissions = roleMenuMapper.selectList(new EntityWrapper<RoleMenu>().eq("menu_id", permission.getId()));
                List<String> roles = new LinkedList<>();
                if (!CollectionUtils.isEmpty(permissions)){
                    permissions.forEach( e -> {
                        Integer roleId = e.getRoleId();
                        Role role = roleMapper.selectById(roleId);
                        roles.add(role.getCode());
                    });
                }
                // 保存该url对应角色权限信息
                return SecurityConfig.createList(roles.toArray(new String[roles.size()]));
            }
        }
        // 如果数据中没有找到相应url资源则为非法访问，要求用户登录再进行操作
        return SecurityConfig.createList(Constants.ROLE_LOGIN);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
