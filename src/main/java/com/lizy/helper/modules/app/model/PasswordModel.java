package com.lizy.helper.modules.app.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lizy
 * @date 2021/8/27 下午2:32
 */
@Data
public class PasswordModel {

    @NotNull(message = "手机号不能为空！")
    private String username;

    private String password;

    private String newPassword;
}
