package com.lizy.helper.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.lizy.helper.modules.common.entity.BaseEntity;
import com.lizy.helper.modules.common.validator.FieldRepeatValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
@Data
@ApiModel(description = "系统管理-菜单表 ")
@TableName("t_sys_menu")
@FieldRepeatValidator(field = "resources", message = "菜单编码重复！")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@ApiModelProperty(value = "主键")
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 上级菜单ID
     */
	@ApiModelProperty(value = "上级菜单ID")
	@TableField("parent_id")
	private String parentId;
	/**
	 * url
	 */
	@ApiModelProperty(value = "url")
	@TableField("url")
	private String url;
    /**
     * 菜单编码
     */
	@ApiModelProperty(value = "菜单编码")
	@TableField("resources")
	@NotBlank(message = "菜单编码不能为空")
	@Length(max = 100, message = "菜单编码不能超过100个字符")
	private String resources;
    /**
     * 菜单名称
     */
	@ApiModelProperty(value = "菜单名称")
	@TableField("title")
	@NotBlank(message = "菜单名称不能为空")
	private String title;
    /**
     * 菜单级别
     */
	@ApiModelProperty(value = "菜单级别")
	@TableField("level")
	private Integer level;
    /**
     * 排序
     */
	@ApiModelProperty(value = "排序")
	@TableField("sort_no")
	private Integer sortNo;
    /**
     * 菜单图标
     */
	@ApiModelProperty(value = "菜单图标")
	@TableField("icon")
	private String icon;
    /**
     * 类型 menu、button
     */
	@ApiModelProperty(value = "类型 menu、button")
	@TableField("type")
	@NotBlank(message = "类型不能为空")
	private String type;
    /**
     * 备注
     */
	@ApiModelProperty(value = "备注")
	@TableField("remarks")
	private String remarks;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
