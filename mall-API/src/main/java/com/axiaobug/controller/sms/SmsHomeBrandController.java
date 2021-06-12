package com.axiaobug.controller.sms;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.sms.SmsHomeBrand;
import com.axiaobug.service.SmsHomeBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * management brand of Index Controller
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "首页品牌管理")
@RequestMapping("/home/brand")
public class SmsHomeBrandController {

    @Resource
    private SmsHomeBrandService homeBrandService;

    @Resource
    private CommonMethod commonMethod;

    @ApiOperation("添加首页推荐品牌")
    @PostMapping(value = "/create")
    public CommonResult<Boolean> create(@RequestBody List<SmsHomeBrand> homeBrandList) throws Exception {
        return commonMethod.response(homeBrandService.create(homeBrandList));
    }

    @ApiOperation("修改推荐品牌排序")
    @PatchMapping(value = "/update/sort/{id}")
    public CommonResult<Boolean> updateSort(@PathVariable Integer id, Integer sort) throws Exception {
        return commonMethod.response(homeBrandService.updateSort(id,sort));
    }

    @ApiOperation("批量删除推荐品牌")
    @DeleteMapping(value = "/delete")
    public CommonResult<Boolean> delete(@RequestParam("ids") List<Integer> ids) throws Exception {
        return commonMethod.response(homeBrandService.delete(ids));
    }

    @ApiOperation("批量修改推荐品牌状态")
    @PatchMapping(value = "/update/recommendStatus")
    public CommonResult<Boolean> updateRecommendStatus(@RequestParam("ids") List<Integer> ids, @RequestParam Integer recommendStatus) throws Exception {
        return commonMethod.response(homeBrandService.updateRecommendStatus(ids,recommendStatus));
    }

    @ApiOperation("分页查询推荐品牌")
    @GetMapping(value = "/list")
    public CommonResult<List<SmsHomeBrand>> list(@RequestParam(value = "brandName", required = false) String brandName,
                                                       @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeBrand> homeBrandList = homeBrandService.list(brandName, recommendStatus, pageSize, pageNum);
        if(!homeBrandList.isEmpty()){
            return CommonResult.success(homeBrandList);
        }
        return CommonResult.failed();
    }
}
