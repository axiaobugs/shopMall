package com.axiaobug.service;

import com.axiaobug.dto.SmsFlashPromotionProduct;
import com.axiaobug.pojo.sms.SmsFlashPromotionProductRelation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface SmsFlashPromotionProductRelationService {
    /**
     * 批量添加关联
     */
    @Transactional
    Boolean create(SmsFlashPromotionProductRelation relation);

    /**
     * 修改关联信息
     */
    Boolean update(Integer id, SmsFlashPromotionProductRelation relation);

    /**
     * 删除关联
     */
    Boolean delete(Integer id) throws Exception;

    /**
     * 获取关联详情
     */
    SmsFlashPromotionProductRelation getItem(Integer id) throws Exception;

    /**
     * 分页查询相关商品及促销信息
     *
     * @param flashPromotionId        限时购id
     * @param flashPromotionSessionId 限时购场次id
     */
    List<SmsFlashPromotionProductRelation> list(Integer flashPromotionId, Integer flashPromotionSessionId, Integer pageSize, Integer pageNum);

    /**
     * 根据活动和场次id获取商品关系数量
     * @param flashPromotionId        限时购id
     * @param flashPromotionSessionId 限时购场次id
     */
    long getCount(Integer flashPromotionId,Integer flashPromotionSessionId);
}
