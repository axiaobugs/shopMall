package com.axiaobug.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@Getter
@Setter
public class OmsOrderDeliveryParam {
    @ApiModelProperty("订单id")
    private Integer id;
    @ApiModelProperty("物流公司")
    private String deliveryCompany;
    @ApiModelProperty("物流单号")
    private String deliverySerialNum;

    @Override
    public String toString() {
        return "OmsOrderDeliveryParam{" +
                "id=" + id +
                ", deliveryCompany='" + deliveryCompany + '\'' +
                ", deliverySerialNum='" + deliverySerialNum + '\'' +
                '}';
    }
}
