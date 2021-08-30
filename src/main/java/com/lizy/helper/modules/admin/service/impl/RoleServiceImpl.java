package com.lizy.helper.modules.admin.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lizy.helper.modules.admin.dto.input.RoleQueryPara;
import com.lizy.helper.modules.admin.entity.Role;
import com.lizy.helper.modules.admin.mapper.RoleMapper;
import com.lizy.helper.modules.admin.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements ISysRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public void listPage(Page<Role> page, RoleQueryPara filter) {
        page.setRecords(roleMapper.selectRoles(page, filter));
    }

    @Override
    public List<Role> list(RoleQueryPara filter) {
        return roleMapper.selectRoles(filter);
    }

    @Override
    public Integer save(Role para) {
        if (para.getId()!=null) {
            roleMapper.updateById(para);
        } else {
            roleMapper.insert(para);
        }
        return para.getId();
    }

}
