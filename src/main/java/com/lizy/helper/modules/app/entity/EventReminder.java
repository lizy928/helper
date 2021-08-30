package com.lizy.helper.modules.app.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * @author lzy
 * @date 2021/8/29
 */
@Data
@TableName("event_reminder")
public class EventReminder {

    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("name")
    private String name;

    /**
     * 0-阳历，1-农历
     */
    @TableField("time_type")
    private Integer timeType;

    @TableField("time")
    private Date time;

    /**
     * 0-不重复，1-重复
     */
    @TableField("repeat")
    private Integer repeat;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;
}
