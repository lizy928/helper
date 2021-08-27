package com.lizy.helper.modules.admin.dto.output;

import com.google.common.collect.Lists;
import com.lizy.helper.modules.admin.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:35
 */
@Data
public class UserTreeNode extends User {

    List<UserTreeNode> children = Lists.newArrayList();

}
