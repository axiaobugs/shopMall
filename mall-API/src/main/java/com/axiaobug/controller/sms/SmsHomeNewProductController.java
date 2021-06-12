package com.axiaobug.controller.sms;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.sms.SmsHomeNewProduct;
import com.axiaobug.service.SmsHomeNewProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * management new product controller
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "首页新品管理")
@RequestMapping("/home/newProduct")
public class SmsHomeNewProductController {

    @Resource
    private SmsHomeNewProductService homeNewProductService;

    @Resource
    private CommonMethod commonMethod;

    @ApiOperation("添加首页新品")
    @PostMapping(value = "/create")
    public CommonResult<Boolean> create(@RequestBody List<SmsHomeNewProduct> homeNewProductList) throws Exception {
        return commonMethod.response(homeNewProductService.create(homeNewProductList));
    }

    @ApiOperation("修改首页新品排序")
    @PatchMapping(value = "/update/sort/{id}")
    public CommonResult<Boolean> updateSort(@PathVariable Integer id, Integer sort) throws Exception {
        return commonMethod.response(homeNewProductService.updateSort(id,sort));
    }

    @ApiOperation("批量删除首页新品")
    @DeleteMapping(value = "/delete")
    public CommonResult<Boolean> delete(@RequestParam("ids") List<Integer> ids) throws Exception {
        return commonMethod.response(homeNewProductService.delete(ids));
    }

    @ApiOperation("批量修改首页新品状态")
    @PatchMapping(value = "/update/recommendStatus")
    public CommonResult<Boolean> updateRecommendStatus(@RequestParam("ids") List<Integer> ids, @RequestParam Integer recommendStatus) throws Exception {
        return commonMethod.response(homeNewProductService.updateRecommendStatus(ids,recommendStatus));
    }

    @ApiOperation("分页查询首页新品")
    @GetMapping(value = "/list")
    public CommonResult<List<SmsHomeNewProduct>> list(@RequestParam(value = "productName", required = false) String productName,
                                                            @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        List<SmsHomeNewProduct> homeNewProductList = homeNewProductService.list(productName, recommendStatus, pageSize, pageNum);
        homeNewProductList.forEach(System.out::println);
        if (!homeNewProductList.isEmpty()){
            return CommonResult.success(homeNewProductList);
        }
        return CommonResult.failed();
    }

}
