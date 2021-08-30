package com.lizy.helper.modules.app.model;

import com.lizy.helper.modules.app.entity.SignInRecord;
import lombok.Data;

import java.util.List;

/**
 * @author lzy
 * @date 2021/8/28
 */
@Data
public class SignInDetailModel {

    private Long id;

    private List<SignInRecord> recordList;
}
