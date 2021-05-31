package com.axiaobug.dto;

import com.axiaobug.pojo.oms.OmsCompanyAddress;
import com.axiaobug.pojo.oms.OmsOrderReturnApply;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public class OmsOrderReturnApplyResult extends OmsOrderReturnApply {
    @Getter
    @Setter
    @ApiModelProperty(value = "公司收货地址")
    private OmsCompanyAddress companyAddress;
}
