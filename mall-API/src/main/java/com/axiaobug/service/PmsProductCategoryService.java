package com.axiaobug.service;

import com.axiaobug.dto.PmsProductCategoryParam;
import com.axiaobug.dto.PmsProductCategoryWithChildrenItem;
import com.axiaobug.pojo.pms.PmsProductCategory;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * management of product category Service
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface PmsProductCategoryService {
    /**
     * 创建商品分类
     */
    @Transactional
    Boolean create(PmsProductCategoryParam pmsProductCategoryParam) throws Exception;

    /**
     * 修改商品分类
     */
    @Transactional
    int update(Integer id, PmsProductCategoryParam pmsProductCategoryParam);

    /**
     * 分页获取商品分类
     */
    List<PmsProductCategory> getList(Integer parentId, Integer pageSize, Integer pageNum);

    /**
     * 删除商品分类
     */
    int delete(Integer id);

    /**
     * 根据ID获取商品分类
     */
    PmsProductCategory getItem (Integer id);

    /**
     * 批量修改导航状态
     */
    int updateNavStatus(List<Integer> ids, Integer navStatus);

    /**
     * 批量修改显示状态
     */
    int updateShowStatus(List<Integer> ids, Integer showStatus);

    /**
     * 以层级形式获取商品分类
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
