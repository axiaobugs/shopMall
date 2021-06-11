package com.axiaobug.controller.sms;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.common.CommonResult;
import com.axiaobug.dto.SmsFlashPromotionProduct;
import com.axiaobug.pojo.sms.SmsFlashPromotionProductRelation;
import com.axiaobug.service.SmsFlashPromotionProductRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * Limited time purchase and product relationship management Controller
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "限时购和商品关系管理")
@RequestMapping("/flashProductRelation")
public class SmsFlashPromotionProductRelationController {

    @Resource
    private SmsFlashPromotionProductRelationService relationService;

    @Resource
    private CommonMethod commonMethod;

    @ApiOperation("添加关联")
    @PostMapping(value = "/create")
    public CommonResult<Boolean> create(@RequestBody SmsFlashPromotionProductRelation relation) {
        return commonMethod.response(relationService.create(relation));
    }

    @ApiOperation("修改关联信息")
    @PostMapping(value = "/update/{id}")
    public CommonResult<Boolean> update(@PathVariable Integer id, @RequestBody SmsFlashPromotionProductRelation relation) {
        return commonMethod.response(relationService.update(id,relation));
    }

    @ApiOperation("删除关联")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult<Boolean> delete(@PathVariable Integer id) throws Exception {
        return commonMethod.response(relationService.delete(id));
    }

    @ApiOperation("获取管理商品促销信息")
    @GetMapping(value = "/{id}")
    public CommonResult<SmsFlashPromotionProductRelation> getItem(@PathVariable Integer id) throws Exception {
        SmsFlashPromotionProductRelation relation = relationService.getItem(id);
        if (relation!=null){
            return CommonResult.success(relation);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分页查询不同场次关联及商品信息")
    @GetMapping(value = "/list")
    public CommonResult<List<SmsFlashPromotionProductRelation>> list(@RequestParam(value = "flashPromotionId") Integer flashPromotionId,
                                                             @RequestParam(value = "flashPromotionSessionId") Integer flashPromotionSessionId,
                                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                             @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        List<SmsFlashPromotionProductRelation> flashPromotionProductList = relationService.list(flashPromotionId, flashPromotionSessionId, pageSize, pageNum);
        return CommonResult.success(flashPromotionProductList);
    }

}
