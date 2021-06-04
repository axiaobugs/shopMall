package com.axiaobug.service;

import com.axiaobug.dto.PmsProductAttributeParam;
import com.axiaobug.dto.ProductAttrInfo;
import com.axiaobug.pojo.pms.PmsProductAttribute;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface PmsProductAttributeService {
    /**
     * 根据分类分页获取商品属性
     * @param cid 分类id
     * @param type 0->属性；1->参数
     */
    List<PmsProductAttribute> getList(Integer cid, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 添加商品属性
     */
    @Transactional
    Boolean create(PmsProductAttributeParam inputParam) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    /**
     * 修改商品属性
     */
    Boolean update(Integer id, PmsProductAttributeParam productAttributeParam);

    /**
     * 获取单个商品属性信息
     */
    PmsProductAttribute getItem(Integer id);

    /**
     * 批量删除商品属性
     */
    @Transactional
    Boolean delete(List<Integer> ids);

    /**
     * 获取商品分类对应属性列表
     */
    List<ProductAttrInfo> getProductAttrInfo(Integer productCategoryId);
}
