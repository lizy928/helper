package com.lizy.helper.modules.common.constans;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
public class Constants {

    /**
     * 接口url
     */
    public static Map<String,String> URL_MAPPING_MAP = new HashMap<>();

    /**
     *  获取项目根目录
     */
    public static String PROJECT_ROOT_DIRECTORY = System.getProperty("user.dir");

    /**
     * 密码加密相关
     */
    public static String SALT = "a2G8";

    public static final int HASH_ITERATIONS = 1;

    /**
     * 请求头 - token
     */
    public static final String REQUEST_HEADER = "token";

    /**
     * 请求头类型：
     * application/x-www-form-urlencoded ： form表单格式
     * application/json ： json格式
     */
    public static final String REQUEST_HEADERS_CONTENT_TYPE = "application/json";

    /**
     * 登录者角色
     */
    public static final String ROLE_LOGIN = "role_login";

    public static final Integer USER_TYPE_COMMON = 0;
    public static final Integer USER_TYPE_ADMIN = 1;

}
