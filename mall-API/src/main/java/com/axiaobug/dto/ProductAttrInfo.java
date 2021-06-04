package com.axiaobug.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品分类对应属性信息
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductAttrInfo {
    @ApiModelProperty("商品属性ID")
    private Integer attributeId;
    @ApiModelProperty("商品属性分类ID")
    private Integer attributeCategoryId;
}
