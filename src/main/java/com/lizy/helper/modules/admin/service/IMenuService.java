package com.lizy.helper.modules.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.lizy.helper.modules.admin.dto.input.MenuQueryPara;
import com.lizy.helper.modules.admin.entity.Menu;

import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 菜单树
     *
     * @param :
     * @return: java.util.List<com.lizy.helper.modules.admin.entity.Menu>
     */
    List<Menu> listTreeMenu();

    /**
     * 系统管理-菜单表 列表分页
     *
     * @param page
     * @param filter
     * @return
     */
    void listPage(Page<Menu> page, MenuQueryPara filter);

    /**
     * 保存系统管理-菜单表
     *
     * @param input
     */
    Integer save(Menu input);

    /**
     * 系统管理-菜单表 列表
     *
     * @param filter
     * @return
     */
    List<Menu> list(MenuQueryPara filter);
}
