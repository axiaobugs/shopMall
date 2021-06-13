package com.axiaobug.controller.sms;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.sms.SmsHomeRecommendProduct;
import com.axiaobug.service.SmsHomeRecommendProductService;
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
@Api(tags = "首页人气推荐管理")
@RequestMapping("/home/recommendProduct")
public class SmsHomeRecommendProductController {
    @Resource
    private SmsHomeRecommendProductService recommendProductService;

    @Resource
    private CommonMethod commonMethod;

    @ApiOperation("添加首页推荐")
    @PostMapping(value = "/create")
    public CommonResult<Boolean> create(@RequestBody List<SmsHomeRecommendProduct> homeRecommendProductList) throws Exception {
        return commonMethod.response(recommendProductService.create(homeRecommendProductList));
    }

    @ApiOperation("修改推荐排序")
    @PatchMapping(value = "/update/sort/{id}")
    public CommonResult<Boolean> updateSort(@PathVariable Integer id,
                                            @RequestParam(name = "sort") Integer sort) {
        return commonMethod.response(recommendProductService.updateSort(id,sort));
    }

    @ApiOperation("批量删除推荐")
    @DeleteMapping(value = "/delete")
    public CommonResult<Boolean> delete(@RequestParam("ids") List<Integer> ids) throws Exception {
        return commonMethod.response(recommendProductService.delete(ids));
    }

    @ApiOperation("批量修改推荐状态")
    @PatchMapping(value = "/update/recommendStatus")
    public CommonResult<Boolean> updateRecommendStatus(@RequestParam("ids") List<Integer> ids,
                                                       @RequestParam("status") Integer recommendStatus) {
        return commonMethod.response(recommendProductService.updateRecommendStatus(ids,recommendStatus));
    }

    @ApiOperation("分页查询推荐")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<SmsHomeRecommendProduct>> list(@RequestParam(value = "productName", required = false) String productName,
                                                                  @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        List<SmsHomeRecommendProduct> homeRecommendProductList = recommendProductService.list(productName, recommendStatus, pageSize, pageNum);
        if (!homeRecommendProductList.isEmpty()){
            return CommonResult.success(homeRecommendProductList);
        }
        return CommonResult.failed();
    }


}
