package com.lizy.helper.config;


import com.lizy.helper.modules.common.handle.LoginUserHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author lzy
 * @date 2021/8/28
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver());
    }

    @Bean
    public LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver() {
        return new LoginUserHandlerMethodArgumentResolver();
    }

}
