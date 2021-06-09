package com.axiaobug.dto;

import com.axiaobug.pojo.sms.SmsFlashPromotionSession;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public class SmsFlashPromotionSessionDetail extends SmsFlashPromotionSession {
    @Setter
    @Getter
    @ApiModelProperty("商品数量")
    private Integer productCount;
}
