package com.axiaobug.security.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Discription: Spring Util
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    /**
     * get applicationContext
     * */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * get Bean from name
     * 通过name获取Bean
     * */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * get bean from class
     * 通过class获取Bean
     * */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * get the specify bean from name and class
     * 通过name,以及Clazz返回指定的Bean
     * */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
