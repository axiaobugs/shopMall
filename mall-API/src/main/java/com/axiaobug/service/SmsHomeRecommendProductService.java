package com.axiaobug.service;

import com.axiaobug.pojo.sms.SmsHomeRecommendProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface SmsHomeRecommendProductService {
    /**
     * 添加首页推荐
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean create(List<SmsHomeRecommendProduct> homeRecommendProductList) throws Exception;

    /**
     * 修改推荐排序
     */
    Boolean updateSort(Integer id, Integer sort);

    /**
     * 批量删除推荐
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean delete(List<Integer> ids) throws Exception;

    /**
     * 批量更新推荐状态
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean updateRecommendStatus(List<Integer> ids, Integer recommendStatus) throws NoSuchMethodException;

    /**
     * 分页查询推荐
     */
    List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}
