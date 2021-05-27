package com.axiaobug.service;

import com.axiaobug.dto.OmsOrderDeliveryParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface OmsOrderDeliveryService{

    /** 
    * @Discription: 
    * @Param: deliveryParamList is a list
    * @return: int 0: success 1: failure
    */
    @Transactional(rollbackFor = Exception.class)
    int delivery(List<OmsOrderDeliveryParam> deliveryParamList);



}
