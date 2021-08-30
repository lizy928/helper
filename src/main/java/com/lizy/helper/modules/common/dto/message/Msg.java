package com.lizy.helper.modules.common.dto.message;

import lombok.Data;

/**
 * @author lzy
 * @date 2021/8/29
 */
@Data
public class Msg {

    private Long id;

    private String time;

    private String type;

    private Userinfo userinfo;

    private Content content;

}
