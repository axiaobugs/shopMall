package com.axiaobug.controller.pms;

import cn.hutool.core.map.MapUtil;
import com.axiaobug.common.CommonMethod;
import com.axiaobug.common.CommonResult;
import com.axiaobug.dto.PmsProductCategoryParam;
import com.axiaobug.dto.PmsProductCategoryWithChildrenItem;
import com.axiaobug.pojo.pms.PmsProductCategory;
import com.axiaobug.service.PmsProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

/**
 * management of product category
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "PmsProductCategoryController")
@RequestMapping("/productCategory")
public class PmsProductCategoryController {

    @Resource
    private PmsProductCategoryService productCategoryService;

    @Resource
    private CommonMethod commonMethod;

    @ApiOperation("添加产品分类")
    @PostMapping(value = "/create")
    public CommonResult<Boolean> create(@Validated @RequestBody PmsProductCategoryParam productCategoryParam) throws Exception {
        Boolean flag = productCategoryService.create(productCategoryParam);
        if (flag) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改商品分类")
    @PatchMapping(value = "/update/{id}")
    public CommonResult<Boolean> update(@PathVariable Integer id,
                               @Validated
                               @RequestBody PmsProductCategoryParam productCategoryParam) throws Exception {
        Boolean flag = productCategoryService.update(id, productCategoryParam);
        if (flag) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("分页查询商品分类")
    @GetMapping(value = "/list/{parentId}")
    public CommonResult<List<PmsProductCategory>> getList(@PathVariable Integer parentId,
                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                          @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        List<PmsProductCategory> productCategoryList = productCategoryService.getList(parentId, pageSize, pageNum);
        if (productCategoryList.size()>0) {
            return CommonResult.success(productCategoryList);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据id获取商品分类")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProductCategory> getItem(@PathVariable Integer id) {
        PmsProductCategory productCategory = productCategoryService.getItem(id);
        HashMap<Object, Object> objectHashMap = commonMethod.gainConditionFromObjectByField(productCategory);
        if (MapUtil.isNotEmpty(objectHashMap)) {
            return CommonResult.success(productCategory);
        }else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除商品分类")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult<Boolean> delete(@PathVariable Integer id) {
        Boolean flag = productCategoryService.delete(id);
        if (flag) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("批量修改导航栏显示状态")
    @PatchMapping(value = "/update/navStatus")
    public CommonResult<Boolean> updateNavStatus(@RequestParam("ids") List<Integer> ids, @RequestParam("navStatus") Integer navStatus) throws Exception {
        Boolean flag = productCategoryService.updateNavStatus(ids, navStatus);
        if (flag) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改显示状态")
    @PatchMapping(value = "/update/showStatus")
    public CommonResult<Boolean> updateShowStatus(@RequestParam("ids") List<Integer> ids, @RequestParam("showStatus") Integer showStatus) throws Exception {
        Boolean flag = productCategoryService.updateShowStatus(ids, showStatus);
        if (flag) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询所有一级分类及子分类")
    @GetMapping(value = "/list/withChildren")
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren() throws Exception {
        List<PmsProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
        return CommonResult.success(list);
    }
}
