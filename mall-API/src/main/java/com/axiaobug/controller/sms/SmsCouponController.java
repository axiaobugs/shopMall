package com.axiaobug.controller.sms;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.common.CommonResult;
import com.axiaobug.dto.SmsCouponParam;
import com.axiaobug.pojo.sms.SmsCoupon;
import com.axiaobug.service.SmsCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * management of coupon Controller
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "SmsCouponController")
@RequestMapping("/coupon")
public class SmsCouponController {

    @Resource
    private SmsCouponService couponService;

    @Resource
    private CommonMethod commonMethod;

    @ApiOperation("添加优惠券")
    @PostMapping(value = "/create")
    public CommonResult<Boolean> add(@RequestBody SmsCoupon couponParam) throws Exception {
        return commonMethod.response(couponService.create(couponParam));
    }

}
