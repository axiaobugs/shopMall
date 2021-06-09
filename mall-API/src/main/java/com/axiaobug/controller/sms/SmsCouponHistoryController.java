package com.axiaobug.controller.sms;

import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.sms.SmsCouponHistory;
import com.axiaobug.service.SmsCouponHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "SmsCouponHistoryController")
@RequestMapping("/couponHistory")
public class SmsCouponHistoryController {

    @Autowired
    private SmsCouponHistoryService historyService;

    @ApiOperation("根据优惠券id，使用状态，订单编号分页获取领取记录")
    @GetMapping(value = "/list")
    public CommonResult<List<SmsCouponHistory>> list(@RequestParam(value = "couponId", required = false) Integer couponId,
                                                     @RequestParam(value = "useStatus", required = false) Integer useStatus,
                                                     @RequestParam(value = "orderSn", required = false) String orderSn,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        List<SmsCouponHistory> historyList = historyService.list(couponId, useStatus, orderSn, pageSize, pageNum);
        return CommonResult.success(historyList);
    }
}
