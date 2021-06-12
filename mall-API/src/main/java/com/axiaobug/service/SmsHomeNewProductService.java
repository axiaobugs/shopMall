package com.axiaobug.service;

import com.axiaobug.pojo.sms.SmsHomeNewProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface SmsHomeNewProductService {
    /**
     * 添加首页推荐
     */
    @Transactional
    Boolean create(List<SmsHomeNewProduct> homeNewProductList) throws Exception;

    /**
     * 修改推荐排序
     */
    Boolean updateSort(Integer id, Integer sort) throws Exception;

    /**
     * 批量删除推荐
     */
    Boolean delete(List<Integer> ids) throws Exception;

    /**
     * 批量更新推荐状态
     */
    Boolean updateRecommendStatus(List<Integer> ids, Integer recommendStatus) throws Exception;

    /**
     * 分页查询推荐
     */
    List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}
