package com.axiaobug.controller.pms;

import com.axiaobug.common.CommonResult;
import com.axiaobug.dto.PmsBrandParam;
import com.axiaobug.pojo.pms.PmsBrand;
import com.axiaobug.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * management product brand controller
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 * TODO: 写所有接口的测试类
 */
@RestController
@Api(tags = "PmsBrandController")
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService brandService;

    @ApiOperation(value = "获取全部品牌列表")
    @GetMapping(value = "/listAll")
    public CommonResult<List<PmsBrand>> getList() {
        return brandService.listAllBrand().isEmpty()?
                CommonResult.failed():
                CommonResult.success(brandService.listAllBrand());
    }


    @ApiOperation(value = "添加品牌")
    @PostMapping(value = "/create")
    public CommonResult<Boolean> create(@Validated @RequestBody PmsBrandParam pmsBrand) {
        Boolean flag = brandService.createBrand(pmsBrand);
        if (flag) {
            return CommonResult.success(true);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "更新品牌")
    @PatchMapping(value = "/update/{id}")
    public CommonResult<Boolean> update(@PathVariable("id") Integer id,
                               @Validated @RequestBody PmsBrandParam pmsBrandParam) {
        Boolean flag = brandService.updateBrand(id, pmsBrandParam);
        if (flag) {
            return CommonResult.success(true);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "删除品牌")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult<Boolean> delete(@PathVariable("id") Integer id) {
        Boolean flag = brandService.deleteBrand(id);
        if (flag) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation(value = "批量删除品牌")
    @DeleteMapping(value = "/delete/batch")
    public CommonResult<Boolean> deleteBatch(@RequestParam("ids") List<Integer> ids) throws Exception {
        Boolean flag = brandService.deleteBrand(ids);
        if (flag) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed("失败");
        }
    }


    @ApiOperation(value = "根据品牌名称获取品牌列表")
    @GetMapping(value = "/list")
    public CommonResult<List<PmsBrand>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                      @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<PmsBrand> brandList = brandService.listBrand(keyword, pageNum, pageSize);
        if (!brandList.isEmpty()) {
            return CommonResult.success(brandList);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "根据编号查询品牌信息")
    @GetMapping(value = "/{id}")
    public CommonResult<PmsBrand> getItem(@PathVariable("id") Integer id) throws Exception {
        return CommonResult.success(brandService.getBrand(id));
    }

    @ApiOperation(value = "批量更新显示状态")
    @PatchMapping(value = "/update/showStatus")
    public CommonResult<Boolean> updateShowStatus(@RequestParam("ids") List<Integer> ids,
                                         @RequestParam("showStatus") Integer showStatus) {
        Boolean flag = brandService.updateShowStatus(ids, showStatus);
        if (flag) {
            return CommonResult.success(true);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "批量更新厂家制造商状态")
    @PatchMapping(value = "/update/factoryStatus")
    public CommonResult<Boolean> updateFactoryStatus(@RequestParam("ids") List<Integer> ids,
                                            @RequestParam("factoryStatus") Integer factoryStatus) {
        Boolean flag = brandService.updateFactoryStatus(ids, factoryStatus);
        if (flag) {
            return CommonResult.success(true);
        }
        return CommonResult.failed();
    }
}
