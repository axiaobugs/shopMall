package com.axiaobug.dto;

import com.axiaobug.pojo.pms.PmsProductAttribute;
import com.axiaobug.pojo.pms.PmsProductAttributeCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {
    @Getter
    @Setter
    @ApiModelProperty(value = "商品属性列表")
    private List<PmsProductAttribute> productAttributeList;
}
