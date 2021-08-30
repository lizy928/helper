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
@TableName("sign_in")
public class SignIn {

    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("name")
    private String name;

    @TableField("desc")
    private String desc;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;

}
