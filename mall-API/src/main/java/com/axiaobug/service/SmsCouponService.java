package com.axiaobug.service;

import com.axiaobug.dto.SmsCouponParam;
import com.axiaobug.pojo.sms.SmsCoupon;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface SmsCouponService {
    /**
     * 添加优惠券
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean create(SmsCoupon couponParam) throws Exception;

    /**
     * 根据优惠券id删除优惠券
     */
    @Transactional
    int delete(Integer id);

    /**
     * 根据优惠券id更新优惠券信息
     */
    @Transactional
    int update(Integer id, SmsCouponParam couponParam);

    /**
     * 分页获取优惠券列表
     */
    List<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 获取优惠券详情
     * @param id 优惠券表id
     */
    SmsCouponParam getItem(Integer id);
}
