package com.axiaobug.controller.sms;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.common.CommonResult;
import com.axiaobug.dto.SmsCouponParam;
import com.axiaobug.pojo.sms.SmsCoupon;
import com.axiaobug.service.SmsCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("删除优惠券")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Integer id) throws Exception {
        return commonMethod.response(couponService.delete(id));
    }

    @ApiOperation("修改优惠券")
    @PatchMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Integer id,@RequestBody SmsCoupon couponParam) throws Exception {
        return commonMethod.response(couponService.update(id,couponParam));
    }


}
