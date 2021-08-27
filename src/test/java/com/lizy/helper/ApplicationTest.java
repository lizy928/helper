package com.lizy.helper;

import com.lizy.helper.modules.admin.entity.User;
import com.lizy.helper.modules.admin.mapper.UserMapper;
import com.lizy.helper.modules.common.constans.Constants;
import com.lizy.helper.utils.PasswordUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 13:33
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test(){
        List<User> users = userMapper.selectList(null);
        users.forEach( e -> {
            e.setPassword(PasswordUtils.encodePassword(e.getPassword(), e.getSalt()));
            e.setSalt(e.getSalt());
            userMapper.updateById(e);
        });
    }
}