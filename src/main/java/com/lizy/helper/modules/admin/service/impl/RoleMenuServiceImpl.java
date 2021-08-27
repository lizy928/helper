package com.lizy.helper.modules.admin.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lizy.helper.modules.admin.dto.input.RoleMenuQueryPara;
import com.lizy.helper.modules.admin.entity.RoleMenu;
import com.lizy.helper.modules.admin.mapper.RoleMenuMapper;
import com.lizy.helper.modules.admin.service.IRoleMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:35
 */
@Service
@Transactional
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Override
    public void listPage(Page<RoleMenu> page, RoleMenuQueryPara filter) {
        page.setRecords(roleMenuMapper.selectRoleMenus(page, filter));
    }

    @Override
    public List<RoleMenu> list(RoleMenuQueryPara filter) {
        return roleMenuMapper.selectRoleMenus(filter);
    }

    @Override
    public void saveRoleMenu(RoleMenuQueryPara para) {
        Integer roleId = para.getRoleId();
        String menuIds = para.getMenuIds();
        roleMenuMapper.deleteByRoleId( roleId );
        if(StringUtils.isNotBlank( menuIds )){
            String[] menuIdArrays = menuIds.split( "," );
            if(menuIdArrays.length > 0){
                for (String menuId : menuIdArrays) {
                    RoleMenu roleMenu = new RoleMenu();
                    roleMenu.setRoleId( roleId );
                    roleMenu.setMenuId( Integer.parseInt( menuId ) );
                    roleMenuMapper.insert( roleMenu );
                }
            }
        }
    }

    @Override
    public Integer save(RoleMenu para) {
        if (para.getId()!=null) {
            roleMenuMapper.updateById(para);
        } else {
            roleMenuMapper.insert(para);
        }
        return para.getId();
    }
}
