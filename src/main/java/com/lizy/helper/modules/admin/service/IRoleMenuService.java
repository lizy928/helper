package com.lizy.helper.modules.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.lizy.helper.modules.admin.dto.input.RoleMenuQueryPara;
import com.lizy.helper.modules.admin.entity.RoleMenu;

import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    /**
     * 系统管理 - 角色-菜单关联表 列表分页
     *
     * @param page
     * @param filter
     * @return
     */
    void listPage(Page<RoleMenu> page, RoleMenuQueryPara filter);

    /**
     * 保存系统管理 - 角色-菜单关联表
     *
     * @param input
     */
    Integer save(RoleMenu input);

    /**
     * 系统管理 - 角色-菜单关联表 列表
     *
     * @param filter
     * @return
     */
    List<RoleMenu> list(RoleMenuQueryPara filter);

    /**
     * 保存角色相关联菜单
     *
     * @param filter:
     * @return: void
     */
    void saveRoleMenu(RoleMenuQueryPara filter);
}
