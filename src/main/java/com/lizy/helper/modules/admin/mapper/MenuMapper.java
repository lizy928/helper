package com.lizy.helper.modules.admin.mapper;

import com.lizy.helper.modules.admin.entity.Menu;
import com.lizy.helper.modules.admin.dto.input.MenuQueryPara;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 列表分页
     *
     * @param page
     * @param filter
     * @return
     */
    List<Menu> selectMenus(Pagination page, @Param("filter") MenuQueryPara filter);

    /**
     * 列表
     *
     * @param filter
     * @return
     */
    List<Menu> selectMenus(@Param("filter") MenuQueryPara filter);

    /**
     * 通过菜单编码获取信息
     *
     * @param resources:
     * @return: com.lizy.helper.modules.admin.entity.Menu
     */
    Menu findByResources(@Param("resources") String resources);

    /**
     * 根据角色查询用户权限
     *
     * @param roleId:
     * @return: java.util.List<com.lizy.helper.modules.admin.entity.Menu>
     */
    List<Menu> selectMenuByRoleId(@Param("roleId") Integer roleId);

}
