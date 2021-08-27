package com.lizy.helper.modules.admin.dto.output;

import com.lizy.helper.modules.admin.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author lizy
 * @date 2021/8/27 10:35
 */
@Data
@ApiModel(description = "系统管理 - 角色表 输出内容")
public class RoleView extends Role {

    @ApiModelProperty(value = "是否已关联系统用户")
    private String isRelatedSysUser;

    @ApiModelProperty(value = "是否已关联系统菜单")
    private String isRelatedSysMenu;

    @ApiModelProperty(value = "是否已关联微信账号")
    private String isRelatedWxAccount;

}
