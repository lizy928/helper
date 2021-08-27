package com.lizy.helper.modules.common.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
@ApiModel(description = "基类查询参数")
@Data
public class BaseQuery extends BasePageQuery{
    @ApiModelProperty(value = "用户ID")
    private Integer userId;
}
