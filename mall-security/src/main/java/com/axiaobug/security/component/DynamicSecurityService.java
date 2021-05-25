package com.axiaobug.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @Discription: Dynamic Security Service
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface DynamicSecurityService {
    /**
     * Load resource ANT wildcard and resource corresponding MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
