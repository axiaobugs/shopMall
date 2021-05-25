package com.axiaobug.security.annotation;

import java.lang.annotation.*;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
