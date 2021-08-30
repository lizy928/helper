package com.lizy.helper.modules.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.lizy.helper.modules.admin.dto.input.RoleQueryPara;
import com.lizy.helper.modules.admin.entity.Role;

import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
public interface ISysRoleService extends IService<Role> {

    /**
     * 系统管理-角色表 列表分页
     *
     * @param page
     * @param filter
     * @return
     */
    void listPage(Page<Role> page, RoleQueryPara filter);

    /**
     * 保存系统管理-角色表
     *
     * @param input
     */
    Integer save(Role input);

    /**
     * 系统管理-角色表 列表
     *
     * @param filter
     * @return
     */
    List<Role> list(RoleQueryPara filter);
}
