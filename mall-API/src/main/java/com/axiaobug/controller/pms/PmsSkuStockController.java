package com.axiaobug.controller.pms;

import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.pms.PmsSkuStock;
import com.axiaobug.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "PmsSkuStockController")
@RequestMapping("/sku")
public class PmsSkuStockController {
    @Autowired
    private PmsSkuStockService skuStockService;

    @ApiOperation("根据商品编号及sku编码模糊搜索sku库存")
    @GetMapping(value = "/{pid}")
    public CommonResult<List<PmsSkuStock>> getList(@PathVariable Integer pid, @RequestParam(value = "keyword",required = false) String keyword) {
        List<PmsSkuStock> skuStockList = skuStockService.getList(pid, keyword);
        return CommonResult.success(skuStockList);
    }

    @ApiOperation("批量更新sku库存信息(sku_code单独一个模块更改)")
    @PatchMapping(value ="/update/{pid}")
    public CommonResult update(@PathVariable Integer pid,@RequestBody List<PmsSkuStock> skuStockList){
        Integer integer = skuStockService.update(pid, skuStockList);
        if(integer > 0){
            return CommonResult.success(integer);
        }else{
            return CommonResult.failed("sku stock param are wrong, please check and commit again. transaction roll back");
        }
    }
}
