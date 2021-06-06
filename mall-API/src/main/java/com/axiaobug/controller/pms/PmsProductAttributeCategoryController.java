package com.axiaobug.controller.pms;

import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.pms.PmsProductAttributeCategory;
import com.axiaobug.service.PmsProductAttributeCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * management of product attribute category in controller layer
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "PmsProductAttributeCategoryController")
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {
    @Autowired
    private PmsProductAttributeCategoryService productAttributeCategoryService;

    @ApiOperation( "添加商品属性分类")
    @PostMapping(value = "/create")
    public CommonResult<Boolean> create(@RequestParam String name) {
        Boolean flag = productAttributeCategoryService.create(name);
        if (flag) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed("Failure to create product attribute category");
        }
    }

    @ApiOperation("修改商品属性分类")
    @PostMapping(value = "/update/{id}")
    public CommonResult<Boolean> update(@PathVariable Integer id, @RequestParam String name) {
        Boolean flag = productAttributeCategoryService.update(id, name);
        if (flag) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed("Failure to update product attribute category which ID is "+id);
        }
    }

    @ApiOperation("删除单个商品属性分类")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult<Boolean> delete(@PathVariable Integer id) {
        Boolean flag = productAttributeCategoryService.delete(id);
        if (flag) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed("Failure to delete by ID: "+id);
        }
    }

    @ApiOperation("获取单个商品属性分类信息")
    @GetMapping(value = "/{id}")
    public CommonResult<PmsProductAttributeCategory> getItem(@PathVariable Integer id) {
        PmsProductAttributeCategory productAttributeCategory = productAttributeCategoryService.getItem(id);
        if (productAttributeCategory!= null){
            return CommonResult.success(productAttributeCategory);
        }
        return CommonResult.failed("Can't get Attribute Category by ID: "+id);
    }

    @ApiOperation("分页获取所有商品属性分类")
    @RequestMapping(value = "/list")
    public CommonResult<List<PmsProductAttributeCategory>> getList(@RequestParam(defaultValue = "5") Integer pageSize,
                                                                   @RequestParam(defaultValue = "1") Integer pageNum) {
        List<PmsProductAttributeCategory> productAttributeCategoryList = productAttributeCategoryService.getList(pageSize, pageNum);
        if (!productAttributeCategoryList.isEmpty()) {
            return CommonResult.success(productAttributeCategoryList);
        } else {
            return CommonResult.failed("Some thing Wrong when fetch the pageable of Attr Cate");
        }
    }



}
