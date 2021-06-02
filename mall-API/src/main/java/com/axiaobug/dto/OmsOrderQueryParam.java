package com.axiaobug.dto;

import com.axiaobug.pojo.pms.PmsProduct;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@Getter
@Setter
public class OmsOrderQueryParam  {
    @ApiModelProperty(value = "分页容量")
    private Integer pageNum;
    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;
    @ApiModelProperty(value = "订单编号")
    private String orderSerialNum;
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;
    @ApiModelProperty(value = "收货人电话")
    private String receiverPhone;
    @ApiModelProperty(value = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer status;
    @ApiModelProperty(value = "订单类型：0->正常订单；1->秒杀订单")
    private Integer orderType;
    @ApiModelProperty(value = "订单来源：0->PC订单；1->app订单")
    private Integer sourceType;
    @ApiModelProperty(value = "订单提交时间")
    private Date createTime;
}
