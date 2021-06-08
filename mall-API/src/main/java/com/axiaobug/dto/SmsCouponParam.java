package com.axiaobug.dto;

import com.axiaobug.pojo.sms.SmsCoupon;
import com.axiaobug.pojo.sms.SmsCouponProductCategoryRelation;
import com.axiaobug.pojo.sms.SmsCouponProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Setter
@Getter
public class SmsCouponParam extends SmsCoupon {

    @ApiModelProperty("优惠券绑定的商品")
    private List<SmsCouponProductRelation> couponProductRelations;

    @ApiModelProperty("优惠券绑定的商品分类")
    private List<SmsCouponProductCategoryRelation> couponProductCategoryRelations;
}
