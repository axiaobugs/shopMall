package com.axiaobug.controller.pms;

import com.axiaobug.common.CommonResult;
import com.axiaobug.dto.PmsProductParam;
import com.axiaobug.dto.PmsProductResult;
import com.axiaobug.pojo.pms.PmsProduct;
import com.axiaobug.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "商品管理")
@RequestMapping("/product")
public class PmsProductController {


    @Resource
    private PmsProductService productService;

    @ApiOperation("创建商品")
    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestBody PmsProduct productParam) {
        int count = productService.create(productParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据商品id获取商品编辑信息")
    @GetMapping(value = "/updateInfo/{id}")
    public CommonResult<PmsProductResult> getUpdateInfo(@PathVariable Integer id) {
        PmsProductResult productResult = productService.getUpdateInfo(id);
        return CommonResult.success(productResult);
    }

    @ApiOperation("根据商品id更新商品信息")
    @PostMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Integer id, @RequestBody PmsProductParam productParam) {
        int count = productService.update(id, productParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
