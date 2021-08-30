package com.lizy.helper;

import com.lizy.helper.config.MyProperties;
import com.lizy.helper.utils.ApplicationContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lizy
 * @date 2021/8/27 11:22
 */
@EnableConfigurationProperties({MyProperties.class})
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(Application.class, args);
        ApplicationContextUtil.setApplicationContext(app);
    }
}