package com.axiaobug.service;

import com.axiaobug.pojo.oms.OmsCompanyAddress;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface OmsCompanyAddressService {

    /**
     * 获取全部收货地址
     */
    List<OmsCompanyAddress> list();
}
