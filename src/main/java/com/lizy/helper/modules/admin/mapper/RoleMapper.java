package com.lizy.helper.modules.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.lizy.helper.modules.admin.dto.input.RoleQueryPara;
import com.lizy.helper.modules.admin.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 列表分页
     *
     * @param page
     * @param filter
     * @return
     */
    List<Role> selectRoles(Pagination page, @Param("filter") RoleQueryPara filter);

    /**
     * 列表
     *
     * @param filter
     * @return
     */
    List<Role> selectRoles(@Param("filter") RoleQueryPara filter);

    /**
     * 通过用户ID查询角色集合
     *
     * @param userId:
     * @return: java.util.List<Role>
     */
    List<Role> selectRoleByUserId(@Param("userId") Integer userId);

    /**
     * 通过菜单ID查询角色集合
     *
     * @param menuId:
     * @return: java.util.List<Role>
     */
    List<Role> selectRoleByMenuId(@Param("menuId") Integer menuId);

}
