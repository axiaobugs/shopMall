package com.axiaobug.service;

import com.axiaobug.pojo.oms.OmsOrderSetting;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface OmsOrderSettingService {
    /**
     * 获取指定订单设置
     */
    OmsOrderSetting getItem(Integer id) throws Exception;

    /**
     * 修改指定订单设置
     */
    Boolean update(OmsOrderSetting orderSetting) throws Exception;
}
