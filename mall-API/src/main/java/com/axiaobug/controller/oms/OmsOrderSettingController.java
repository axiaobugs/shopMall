package com.axiaobug.controller.oms;

import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.oms.OmsOrderSetting;
import com.axiaobug.service.OmsOrderSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "OmsOrderSettingController")
@RequestMapping("/orderSetting")
public class OmsOrderSettingController {
    @Autowired
    private OmsOrderSettingService orderSettingService;

    @ApiOperation("获取指定订单设置")
    @GetMapping(value = "/{id}")
    public CommonResult<OmsOrderSetting> getItem(@PathVariable Integer id){
        OmsOrderSetting orderSetting = null;
        try {
            orderSetting = orderSettingService.getItem(id);
            return CommonResult.success(orderSetting);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
    }

    @ApiOperation("修改指定订单设置")
    @PatchMapping(value = "/update")
    public CommonResult<Boolean> update(@RequestBody OmsOrderSetting orderSetting) throws Exception {
        Boolean flag = orderSettingService.update(orderSetting);
        if(flag){
            return CommonResult.success(true);
        }
        return CommonResult.failed("Wrong update order Setting");
    }

}
