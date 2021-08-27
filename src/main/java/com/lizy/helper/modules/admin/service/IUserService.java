package com.lizy.helper.modules.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.lizy.helper.modules.admin.dto.input.UserQueryPara;
import com.lizy.helper.modules.admin.dto.model.UserInfoVO;
import com.lizy.helper.modules.admin.entity.User;

import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
public interface IUserService extends IService<User> {

    /**
     * 系统管理-用户基础信息表列表分页
     *
     * @param page
     * @param filter
     * @return
     */
    void listPage(Page<User> page, UserQueryPara filter);

    /**
     * 保存系统管理-用户基础信息表
     *
     * @param input:
     * @return: java.lang.Integer
     */
    Integer save(User input);

    /**
     * 修改用户个人信息
     *
     * @param para:
     * @return: java.lang.Integer
     */
    Integer updatePersonalInfo(User para);

    /**
     * 系统管理-用户基础信息表列表
     *
     * @param filter
     * @return
     */
    List<User> list(UserQueryPara filter);

    /**
     * 通过token获取用户信息
     *
     * @param token:
     * @return: com.lizy.helper.modules.admin.dto.model.UserInfoVO
     */
    UserInfoVO getCurrentUserInfo(String token);

}
