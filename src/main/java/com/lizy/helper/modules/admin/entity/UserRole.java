package com.lizy.helper.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.lizy.helper.modules.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
@Data
@ApiModel(description = "系统管理 - 用户角色关联表 ")
@TableName("t_sys_user_role")
public class UserRole extends BaseEntity<UserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@ApiModelProperty(value = "主键")
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户ID
     */
	@ApiModelProperty(value = "用户ID")
	@TableField("user_id")
	private Integer userId;
    /**
     * 角色ID
     */
	@ApiModelProperty(value = "角色ID")
	@TableField("role_id")
	private Integer roleId;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
