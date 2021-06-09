package com.axiaobug.controller.sms;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.common.CommonResult;
import com.axiaobug.dto.SmsCouponParam;
import com.axiaobug.pojo.sms.SmsCoupon;
import com.axiaobug.service.SmsCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public CommonResult<Boolean> delete(@PathVariable Integer id) throws Exception {
        return commonMethod.response(couponService.delete(id));
    }

    @ApiOperation("修改优惠券")
    @PatchMapping(value = "/update/{id}")
    public CommonResult<Boolean> update(@PathVariable Integer id,@RequestBody SmsCoupon couponParam) throws Exception {
        return commonMethod.response(couponService.update(id,couponParam));
    }

    @ApiOperation("根据优惠券名称和类型分页获取优惠券列表")
    @GetMapping(value = "/list")
    public CommonResult<List<SmsCoupon>> list(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "type",required = false) Integer type,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        List<SmsCoupon> couponList = couponService.list(name,type,pageSize,pageNum);
        return CommonResult.success(couponList);
    }

    @ApiOperation("获取单个优惠券的详细信息")
    @GetMapping(value = "/{id}")
    public CommonResult<SmsCoupon> getItem(@PathVariable Integer id) {
        SmsCoupon coupon = couponService.getItem(id);
        if (coupon!=null){
            return CommonResult.success(coupon);
        }
        return CommonResult.failed();
    }
}
