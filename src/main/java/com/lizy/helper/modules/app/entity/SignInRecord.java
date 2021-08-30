package com.lizy.helper.modules.app.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * @author dliony
 */
@Data
@TableName("sign_in_record")
public class SignInRecord {

    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;

    @TableField("sign_in_id")
    private Long signInId;

    @TableField("remark")
    private String remark;

    /**
     * 0-补签，1-签到
     */
    @TableField("type")
    private Integer type;

    @TableField("sign_in_time")
    private Date signInTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;

}
