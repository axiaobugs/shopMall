package com.axiaobug.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsProductQueryParam {
    @ApiModelProperty("上架状态")
    private Integer publishStatus;
    @ApiModelProperty("审核状态")
    private Integer verifyStatus;
    @ApiModelProperty("商品名称模糊关键字")
    private String keywords;
    @ApiModelProperty("商品货号")
    private String productSerialNumber;
    @ApiModelProperty("商品分类编号")
    private Integer productCategoryId;
    @ApiModelProperty("商品品牌编号")
    private Integer brandId;
}
