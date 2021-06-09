package com.axiaobug.service;

import com.axiaobug.dto.SmsFlashPromotionSessionDetail;
import com.axiaobug.pojo.sms.SmsFlashPromotionSession;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface SmsFlashPromotionSessionService {
    /**
     * 添加场次
     */
    Boolean create(SmsFlashPromotionSession promotionSession);

    /**
     * 修改场次
     */
    Boolean update(Integer id, SmsFlashPromotionSession promotionSession) throws Exception;

    /**
     * 修改场次启用状态
     */
    Boolean updateStatus(Integer id, Integer status) throws Exception;

    /**
     * 删除场次
     */
    Boolean delete(Integer id) throws Exception;

    /**
     * 获取详情
     */
    SmsFlashPromotionSession getItem(Integer id);

    /**
     * 根据启用状态获取场次列表
     */
    List<SmsFlashPromotionSession> list();

    /**
     * 获取全部可选场次及其数量
     */
    List<SmsFlashPromotionSessionDetail> selectList(Integer flashPromotionId);
}
