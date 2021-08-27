package com.lizy.helper.modules.admin.dto.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:35
 */
@Data
public class MenuVO implements Serializable {

    private Integer id;

    /**
     * 上级菜单ID
     */
    private String parentId;

    /**
     * 菜单编码
     */
    private String resources;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单级别
     */
    private Integer level;

    /**
     * 菜单图标
     */
    private String icon;

    List<MenuVO> children = Lists.newArrayList();

}
