package com.axiaobug.service;

import com.axiaobug.dto.PmsProductParam;
import com.axiaobug.dto.PmsProductQueryParam;
import com.axiaobug.dto.PmsProductResult;
import com.axiaobug.pojo.pms.PmsProduct;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Management of product Service
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface PmsProductService {
    /**
     * 创建商品
     */
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    int create(PmsProduct product);

    /**
     * 根据商品编号获取更新信息
     */
    PmsProductResult getUpdateInfo(Integer id);

    /**
     * 更新商品
     */
    @Transactional
    int update(Integer id, PmsProductParam product);

    /**
     * 分页查询商品
     */
    List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);

    List<PmsProduct> list(String keyword,String field);

    /**
     * 批量修改审核状态
     * @param ids 产品id
     * @param verifyStatus 审核状态
     * @param detail 审核详情
     */
    @Transactional
    Boolean updateVerifyStatus(List<Integer> ids, Integer verifyStatus, String detail) throws Exception;

    /**
     * 批量修改商品上架状态
     */
    Boolean updatePublishStatus(List<Integer> ids, Integer publishStatus) throws Exception;

    /**
     * 批量修改商品推荐状态
     */
    Boolean updateRecommendStatus(List<Integer> ids, Integer recommendStatus) throws Exception;

    /**
     * 批量修改新品状Integer
     * /
    int updateNewStatus(List<Integer> ids, Integer newStatus);

    /**
     * 批量删除商品
     */
    Boolean updateDeleteStatus(List<Integer> ids, Integer deleteStatus) throws Exception;

    /**
     * 根据商品名称或者货号模糊查询
     */
    List<PmsProduct> list(String keyword);
}
