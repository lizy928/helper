package com.lizy.helper.modules.app.entity;

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
@TableName("chat_record")
public class ChatRecord {

    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;

    @TableField("from")
    private Long from;

    @TableField("to")
    private Long to;

    @TableField("type")
    private String type;

    @TableField("text")
    private String text;

    @TableField("url")
    private String url;

    @TableField("w")
    private Integer w;

    @TableField("h")
    private Integer h;

    @TableField("length")
    private Integer length;

    @TableField("create_time")
    private Date createTime;

}
