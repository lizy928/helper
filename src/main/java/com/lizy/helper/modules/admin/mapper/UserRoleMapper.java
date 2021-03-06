package com.lizy.helper.modules.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.lizy.helper.modules.admin.dto.input.UserRoleQueryPara;
import com.lizy.helper.modules.admin.entity.Role;
import com.lizy.helper.modules.admin.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 列表分页
     *
     * @param page
     * @param filter
     * @return
     */
    List<UserRole> selectUserRoles(Pagination page, @Param("filter") UserRoleQueryPara filter);

    /**
     * 列表
     *
     * @param filter
     * @return
     */
    List<UserRole> selectUserRoles(@Param("filter") UserRoleQueryPara filter);

    /**
     * 根据角色Id删除用户与角色相关联数据
     *
     * @param roleId:
     * @return: void
     */
    void deleteByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据用户Id查询关联角色
     *
     * @param userId:
     * @return: java.util.List<com.lizy.helper.modules.admin.entity.Role>
     */
    List<Role> selectRoleByUserId(@Param("userId") Integer userId);

}
