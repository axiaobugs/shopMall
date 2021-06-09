package com.axiaobug.dto;

import com.axiaobug.pojo.pms.PmsProduct;
import com.axiaobug.pojo.sms.SmsFlashPromotionProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public class SmsFlashPromotionProduct extends SmsFlashPromotionProductRelation {
    @Getter
    @Setter
    @ApiModelProperty("关联商品")
    private Integer productId;
}
