package com.axiaobug.service;

import com.axiaobug.pojo.sms.SmsHomeBrand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface SmsHomeBrandService {
    /**
     * 添加首页品牌推荐
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean create(List<SmsHomeBrand> homeBrandList) throws Exception;

    /**
     * 修改品牌推荐排序
     */
    Boolean updateSort(Integer id, Integer sort) throws Exception;

    /**
     * 批量删除品牌推荐
     */
    Boolean delete(List<Integer> ids) throws Exception;

    /**
     * 批量更新推荐状态
     */
    Boolean updateRecommendStatus(List<Integer> ids, Integer recommendStatus) throws Exception;

    /**
     * 分页查询品牌推荐
     */
    List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}
