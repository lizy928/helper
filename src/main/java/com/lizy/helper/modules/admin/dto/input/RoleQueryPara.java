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
@ApiModel(description = "系统管理-角色表 查询参数")
public class RoleQueryPara extends BasePageQuery{
    @ApiModelProperty(value = "角色id")
    private Integer id;
    @ApiModelProperty(value = "角色名称")
    private String name;
}
