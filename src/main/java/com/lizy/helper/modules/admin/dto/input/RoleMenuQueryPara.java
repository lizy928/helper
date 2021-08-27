package com.lizy.helper.modules.admin.dto.input;

import com.lizy.helper.modules.common.dto.input.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lizy
 * @date 2021/8/27 10:35
 */
@Data
@ApiModel(description = "系统管理 - 角色-菜单关联表 查询参数")
public class RoleMenuQueryPara extends BasePageQuery{
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "角色ID")
    private Integer roleId;
    @ApiModelProperty(value = "菜单ids")
    private String menuIds;
}
