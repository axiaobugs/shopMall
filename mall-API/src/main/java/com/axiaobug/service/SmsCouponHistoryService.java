package com.axiaobug.service;

import com.axiaobug.pojo.sms.SmsCouponHistory;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface SmsCouponHistoryService {
    /**
     * 分页查询优惠券领取记录
     * @param couponId 优惠券id
     * @param useStatus 使用状态
     * @param orderSn 使用订单号码
     */
    List<SmsCouponHistory> list(Integer couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum);
}
