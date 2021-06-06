package com.axiaobug.dto;

import com.axiaobug.pojo.pms.PmsProduct;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询单个产品进行修改时返回的结果
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public class PmsProductResult extends PmsProduct {
    @Getter
    @Setter
    @ApiModelProperty("商品所选分类的父id")
    private Integer cateParentId;
}
