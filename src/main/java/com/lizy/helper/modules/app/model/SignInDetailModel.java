package com.lizy.helper.modules.app.model;

import lombok.Data;

import java.util.List;

/**
 * @author lzy
 * @date 2021/8/28
 */
@Data
public class SignInDetailModel {

    private Long id;

    private String name;

    private String desc;

    private List<SignInRecordModel> recordList;

    private Integer monthCount;

    private Integer totalCount;

}
