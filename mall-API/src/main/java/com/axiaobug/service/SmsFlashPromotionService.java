package com.axiaobug.service;

import com.axiaobug.pojo.sms.SmsFlashPromotion;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface SmsFlashPromotionService {
    /**
     * 添加活动
     */
    Boolean create(SmsFlashPromotion flashPromotion) throws Exception;

    /**
     * 修改指定活动
     */
    Boolean update(Integer id, SmsFlashPromotion flashPromotion) throws Exception;

    /**
     * 删除单个活动
     */
    Boolean delete(Integer id) throws Exception;

    /**
     * 修改上下线状态
     */
    Boolean updateStatus(Integer id, Integer status) throws Exception;

    /**
     * 获取详细信息
     */
    SmsFlashPromotion getItem(Integer id);

    /**
     * 分页查询活动
     */
    List<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum);
}
