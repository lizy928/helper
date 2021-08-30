package com.lizy.helper.modules.app.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author lzy
 * @date 2021/8/28
 */
@Data
public class SignInRecordModel {

    /**
     * 主键
     */
    private Long id;

    private Long signInId;

    private String remark;

    @JSONField(format="yyyy-MM-dd")
    private Date signInTime;

    /**
     * 0-补签，1-签到
     */
    private Integer type;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
