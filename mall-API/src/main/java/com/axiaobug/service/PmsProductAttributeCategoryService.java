package com.axiaobug.service;

import com.axiaobug.dto.PmsProductAttributeCategoryItem;
import com.axiaobug.pojo.pms.PmsProductAttributeCategory;

import java.util.List;

/**
 * management of product attribute category
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface PmsProductAttributeCategoryService {
    /**
     * 创建属性分类
     */
    Boolean create(String name);

    /**
     * 修改属性分类
     */
    Boolean update(Integer id, String name);

    /**
     * 删除属性分类
     */
    Boolean delete(Integer id);

    /**
     * 获取属性分类详情
     */
    PmsProductAttributeCategory getItem(Integer id);

    /**
     * 分页查询属性分类
     */
    List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum);

}
