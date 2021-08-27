package com.lizy.helper.modules.admin.dto.input;

import com.lizy.helper.modules.common.dto.input.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author lizy
 * @date 2021/8/27 10:35
 */
@Data
@ApiModel(description = "系统管理 - 日志表查询参数")
public class LogQueryPara extends BasePageQuery{
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "访问人")
    private String username;
    @ApiModelProperty(value = "访问url")
    private String url;
    @ApiModelProperty(value = "访问开始时间")
    private Date startTime;
    @ApiModelProperty(value = "访问结束时间")
    private Date endTime;
}
