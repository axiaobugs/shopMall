package com.axiaobug.controller.pms;

import com.axiaobug.common.CommonResult;
import com.axiaobug.dto.PmsProductCategoryParam;
import com.axiaobug.service.PmsProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;

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

}
