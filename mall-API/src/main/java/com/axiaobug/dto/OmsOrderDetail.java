package com.axiaobug.dto;

import com.axiaobug.pojo.oms.OmsOrder;
import com.axiaobug.pojo.oms.OmsOrderItem;
import com.axiaobug.pojo.oms.OmsOrderOperateHistory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public class OmsOrderDetail  {
    @Getter
    @Setter
    @ApiModelProperty("订单")
    private OmsOrder omsOrder;

    @Getter
    @Setter
    @ApiModelProperty("订单商品列表")
    private List<OmsOrderItem> orderItemList;

    @Getter
    @Setter
    @ApiModelProperty("订单操作记录列表")
    private List<OmsOrderOperateHistory> historyList;

    public OmsOrderDetail(OmsOrder omsOrder,
                          List<OmsOrderItem> orderItemList,
                          List<OmsOrderOperateHistory> historyList) {
        this.omsOrder = omsOrder;
        this.orderItemList = orderItemList;
        this.historyList = historyList;
    }
}
