package com.axiaobug.controller.pms;

import com.axiaobug.common.CommonResult;
import com.axiaobug.dto.PmsProductAttributeParam;
import com.axiaobug.dto.ProductAttrInfo;
import com.axiaobug.pojo.pms.PmsProductAttribute;
import com.axiaobug.service.PmsProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * manage product attribute
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "PmsProductAttributeController")
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Resource
    private PmsProductAttributeService productAttributeService;

    @ApiOperation("根据分类查询属性列表或参数列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0表示属性，1表示参数", required = true, paramType = "query", dataType = "integer")})
    @GetMapping(value = "/list/{cid}")
    public CommonResult<List<PmsProductAttribute>> getList(@PathVariable Integer cid,
                                                                 @RequestParam(value = "type") Integer type,
                                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                 @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        List<PmsProductAttribute> productAttributeList = productAttributeService.getList(cid, type, pageSize, pageNum);
        return CommonResult.success(productAttributeList);
    }

    @ApiOperation("添加商品属性信息")
    @PostMapping(value = "/create")
    public CommonResult<Boolean> create(@RequestBody PmsProductAttributeParam productAttributeParam) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {
        Boolean flag = productAttributeService.create(productAttributeParam);
        if (flag) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改商品属性信息")
    @PatchMapping(value = "/update/{id}")
    public CommonResult<Boolean> update(@PathVariable Integer id, @RequestBody PmsProductAttributeParam productAttributeParam) {
        Boolean flag = productAttributeService.update(id, productAttributeParam);
        if (flag) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询单个商品属性")
    @GetMapping(value = "/{id}")
    public CommonResult<PmsProductAttribute> getItem(@PathVariable Integer id) {
        PmsProductAttribute productAttribute = productAttributeService.getItem(id);
        return productAttribute!=null?CommonResult.success(productAttribute):CommonResult.failed();
    }

    @ApiOperation("批量删除商品属性")
    @DeleteMapping(value = "/delete")
    public CommonResult<Boolean> delete(@RequestParam("ids") List<Integer> ids) {
        Boolean flag = productAttributeService.delete(ids);
        if (flag) {
            return CommonResult.success(true);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据商品分类的id获取商品属性及属性分类")
    @GetMapping(value = "/attrInfo/{productCategoryId}")
    public CommonResult<List<ProductAttrInfo>> getAttrInfo(@PathVariable Integer productCategoryId) {
        List<ProductAttrInfo> productAttrInfoList = productAttributeService.getProductAttrInfo(productCategoryId);
        if (productAttrInfoList.size()!=0) {
            return CommonResult.success(productAttrInfoList);
        }
        return CommonResult.failed();
    }


}
