package com.axiaobug.service;

import com.axiaobug.pojo.pms.PmsSkuStock;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Product SKU invent management service
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface PmsSkuStockService {
    /**
     * 根据产品id和skuCode模糊搜索
     */
    List<PmsSkuStock> getList(Integer pid, String keyword);

    /**
     * 批量更新商品库存信息
     */
    @Transactional(rollbackFor = Exception.class)
    Integer update(Integer pid, List<PmsSkuStock> skuStockList);
}
