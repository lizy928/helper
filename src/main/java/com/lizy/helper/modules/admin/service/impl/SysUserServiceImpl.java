package com.lizy.helper.modules.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lizy.helper.modules.admin.dto.input.UserQueryPara;
import com.lizy.helper.modules.admin.dto.model.ButtonVO;
import com.lizy.helper.modules.admin.dto.model.MenuVO;
import com.lizy.helper.modules.admin.dto.model.UserInfoVO;
import com.lizy.helper.modules.admin.entity.Menu;
import com.lizy.helper.modules.admin.entity.Role;
import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.admin.mapper.RoleMenuMapper;
import com.lizy.helper.modules.admin.mapper.UserMapper;
import com.lizy.helper.modules.admin.mapper.UserRoleMapper;
import com.lizy.helper.modules.admin.service.ISysUserService;
import com.lizy.helper.modules.common.exception.MyException;
import com.lizy.helper.utils.CommonUtil;
import com.lizy.helper.utils.PasswordUtils;
import com.lizy.helper.utils.TreeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<UserMapper, User> implements ISysUserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Override
    public void listPage(Page<User> page, UserQueryPara filter) {
        page.setRecords(userMapper.selectUsers(page, filter));
    }

    @Override
    public List<User> list(UserQueryPara filter) {
        return userMapper.selectUsers(filter);
    }

    @Override
    public UserInfoVO getCurrentUserInfo(String token) {
        User user = userMapper.getUserInfoByToken(token);
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtil.copyProperties( user,userInfoVO);

        Set<String> roles = new HashSet();
        Set<MenuVO> menuVOS = new HashSet();
        Set<ButtonVO> buttonVOS = new HashSet();

        //???????????????????????????
        List<Role> roleList = userRoleMapper.selectRoleByUserId( user.getId() );
        if(roleList != null && !roleList.isEmpty() ){
            roles.add( roleList.get(0).getCode() );

            //???????????????????????????
            List<Menu> menuList = roleMenuMapper.selectMenusByRoleId( roleList.get(0).getId() );
            if(menuList != null && !menuList.isEmpty() ){
                menuList.stream().filter(Objects::nonNull).forEach(menu -> {
                    if ("button".equals(menu.getType().toLowerCase())) {
                        //????????????????????????????????????????????????
                        ButtonVO buttonVO = new ButtonVO();
                        BeanUtil.copyProperties(menu, buttonVO);
                        buttonVOS.add(buttonVO);
                    }
                    if ("menu".equals(menu.getType().toLowerCase())) {
                        //????????????????????????????????????????????????
                        MenuVO menuVO = new MenuVO();
                        BeanUtil.copyProperties(menu, menuVO);
                        menuVOS.add(menuVO);
                    }
                });
            }
        }
        userInfoVO.getRoles().addAll( roles );
        userInfoVO.getButtons().addAll( buttonVOS );
        userInfoVO.getMenus().addAll( TreeBuilder.buildTree(menuVOS) );
        return userInfoVO;
    }

    @Override
    public Integer save(User para) {
        if (para.getId()!=null) {
            User user = userMapper.selectById(para.getId());
            para.setPassword( PasswordUtils.encodePassword(para.getPwd(), user.getSalt()));
            userMapper.updateById(para);
        } else {
            final String salt = CommonUtil.generateSalt();
            para.setSalt(salt);
            para.setPassword( PasswordUtils.encodePassword(para.getPwd(), salt));
            userMapper.insert(para);
        }
        return para.getId();
    }

    @Override
    public Integer updatePersonalInfo(User para) {
        if (para.getId()==null){
            throw new MyException("?????????????????????????????????????????????????????????????????????");
        }
        if ( StringUtils.isBlank( para.getUsername() ) ){
            throw new MyException("?????????????????????");
        }
        if ( StringUtils.isBlank( para.getNickName() ) ){
            throw new MyException("?????????????????????");
        }
        User user = userMapper.selectById( para.getId() );
        if ( StringUtils.isNotBlank( para.getPwd() ) ){
            if (para.getPwd().trim().length() < 6){
                throw new MyException("???????????????6???????????????");
            }
            // ????????????
            para.setPassword( PasswordUtils.encodePassword(para.getPwd(), user.getSalt()));
        } else {
            para.setPwd(null);
        }

        // ????????????????????????
        UserQueryPara userQueryPara = new UserQueryPara();
        userQueryPara.setAccount( para.getUsername() );
        List<User> userList = userMapper.selectUsers(userQueryPara);
        if ( !CollectionUtils.isEmpty( userList ) ){
            if ( !para.getUsername().equals( user.getUsername() ) || userList.size() > 1 ){
                throw new MyException( "?????????????????????????????????" );
            }
        }
        userMapper.updateById(para);
        return para.getId();
    }

}
