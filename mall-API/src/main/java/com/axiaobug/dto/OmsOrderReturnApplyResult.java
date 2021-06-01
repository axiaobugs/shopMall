package com.axiaobug.dto;

import com.axiaobug.pojo.oms.OmsCompanyAddress;
import com.axiaobug.pojo.oms.OmsOrderReturnApply;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OmsOrderReturnApplyResult{

    @ApiModelProperty(value = "公司收货地址")
    private OmsCompanyAddress companyAddress;

    @ApiModelProperty(value = "退货申请详情")
    private OmsOrderReturnApply returnApply;

}
