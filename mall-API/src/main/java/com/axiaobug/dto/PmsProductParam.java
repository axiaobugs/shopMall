package com.axiaobug.dto;

import com.axiaobug.pojo.cms.CmsPrefrenceAreaProductRelation;
import com.axiaobug.pojo.cms.CmsSubjectProductRelation;
import com.axiaobug.pojo.pms.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 创建和修改商品时使用的参数
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsProductParam extends PmsProduct {
    @ApiModelProperty("商品阶梯价格设置")
    private List<PmsProductLadder> productLadderList = null;
    @ApiModelProperty("商品满减价格设置")
    private List<PmsProductFullReduction> productFullReductionList = null;

    @ApiModelProperty("商品的sku库存信息")
    private List<PmsSkuStock> skuStockList = null;
    @ApiModelProperty("商品参数及自定义规格属性")
    private List<PmsProductAttributeValue> productAttributeValueList = null;
    @ApiModelProperty("专题和商品关系")
    private List<CmsSubjectProductRelation> subjectProductRelationList = null;
    @ApiModelProperty("优选专区和商品的关系")
    private List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList = null;


}
