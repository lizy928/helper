package com.lizy.helper.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lizy
 * @date 2021/8/27 10:44
 */
@Slf4j
public class ApplicationContextUtil {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext ac)
            throws BeansException {
        applicationContext = ac;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据Class类型在IOC容器中获取对象
     *
     * @param clazz Class类型
     * @return 对象
     */
    public static <T> List<T> getBeanByType(Class<T> clazz) {
        List<T> list = new ArrayList<T>();

        /* 获取接口的所有实例名 */
        String[] beanNames = applicationContext.getBeanNamesForType(clazz);

        log.debug("getBeanByType beanNames : " + beanNames == null ? "" : Arrays.toString(beanNames));

        if (beanNames == null || beanNames.length == 0) {
            return list;
        }

        T t = null;
        for (String beanName : beanNames) {
            t = (T) applicationContext.getBean(beanName);
            list.add(t);
        }

        return list;
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
}