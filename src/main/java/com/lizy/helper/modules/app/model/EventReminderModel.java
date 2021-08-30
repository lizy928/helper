package com.lizy.helper.modules.app.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author lzy
 * @date 2021/8/29
 */
@Data
public class EventReminderModel {

    private Long id;

    private String name;

    private Integer day;

    private Integer hour;

    private Integer minute;

    private Integer second;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
