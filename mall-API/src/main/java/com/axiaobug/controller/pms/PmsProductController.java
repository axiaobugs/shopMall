package com.axiaobug.controller.pms;

import com.axiaobug.common.CommonResult;
import com.axiaobug.dto.PmsProductParam;
import com.axiaobug.dto.PmsProductQueryParam;
import com.axiaobug.dto.PmsProductResult;
import com.axiaobug.pojo.pms.PmsProduct;
import com.axiaobug.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public CommonResult<Integer> update(@PathVariable Integer id, @RequestBody PmsProductParam productParam) {
        int count = productService.update(id, productParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询商品")
    @GetMapping(value = "/list")
    public CommonResult<List<PmsProduct>> getList(@RequestBody PmsProductQueryParam productQueryParam,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        System.out.println(productQueryParam.toString());
        List<PmsProduct> productList = productService.list(productQueryParam, pageSize, pageNum);
        return CommonResult.success(productList);
    }

    @ApiOperation("根据商品名称或货号模糊查询")
    @GetMapping(value = "/simpleList")
    public CommonResult<List<PmsProduct>> getList(@RequestParam(name = "keyword") String keyword,
                                                  @RequestParam(name = "field") String field) {
        List<PmsProduct> productList = productService.list(keyword,field);
        return CommonResult.success(productList);
    }

    @ApiOperation("批量修改审核状态")
    @PatchMapping(value = "/update/verifyStatus")
    public CommonResult<Boolean> updateVerifyStatus(@RequestParam("ids") List<Integer> ids,
                                           @RequestParam("verifyStatus") Integer verifyStatus,
                                           @RequestParam("detail") String detail) throws Exception {
       return response(productService.updateVerifyStatus(ids, verifyStatus, detail));
    }

    @ApiOperation("批量上下架")
    @PatchMapping(value = "/update/publishStatus")
    public CommonResult<Boolean> updatePublishStatus(@RequestParam("ids") List<Integer> ids,
                                            @RequestParam("publishStatus") Integer publishStatus) throws Exception {
        return response(productService.updatePublishStatus(ids, publishStatus));
    }

    @ApiOperation("批量推荐商品")
    @PatchMapping(value = "/update/recommendStatus")
    public CommonResult<Boolean> updateRecommendStatus(@RequestParam("ids") List<Integer> ids,
                                                       @RequestParam("recommendStatus") Integer recommendStatus) throws Exception {
        return response(productService.updateRecommendStatus(ids, recommendStatus));
    }

    @ApiOperation("批量修改删除状态")
    @PatchMapping(value = "/update/deleteStatus")
    public CommonResult<Boolean> updateDeleteStatus(@RequestParam("ids") List<Integer> ids,
                                           @RequestParam("deleteStatus") Integer deleteStatus) throws Exception {
        return response(productService.updateDeleteStatus(ids, deleteStatus));
    }


    private CommonResult<Boolean> response(Boolean flag){
        if(flag){
            return CommonResult.success(true);
        }
        return CommonResult.failed();
    }


}
