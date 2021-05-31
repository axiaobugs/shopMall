package com.axiaobug.dto;

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
public class OmsReturnApplyQueryParam {
    @ApiModelProperty("分页页数")
    private Integer pageNum;
    @ApiModelProperty("分页大小")
    private Integer pageSize;
    @ApiModelProperty("服务单号")
    private Integer id;
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;
    @ApiModelProperty(value = "收货人号码")
    private String receiverPhone;
    @ApiModelProperty(value = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private Integer status;
    @ApiModelProperty(value = "申请时间")
    private Date createTime;
    @ApiModelProperty(value = "处理人员")
    private String handleMan;
    @ApiModelProperty(value = "处理时间")
    private Date handleTime;
}
