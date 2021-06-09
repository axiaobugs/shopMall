package com.axiaobug.controller.sms;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.sms.SmsFlashPromotion;
import com.axiaobug.service.SmsFlashPromotionService;
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
@Api(tags = "SmsFlashPromotionController")
@RequestMapping("/flash")
public class SmsFlashPromotionController {

    @Resource
    private SmsFlashPromotionService flashPromotionService;

    @Resource
    private CommonMethod commonMethod;

    @ApiOperation("添加活动")
    @PostMapping(value = "/create")
    public CommonResult<Boolean> create(@RequestBody SmsFlashPromotion flashPromotion) throws Exception {
        return commonMethod.response(flashPromotionService.create(flashPromotion));
    }

    @ApiOperation("编辑活动")
    @PostMapping(value = "/update/{id}")
    public Object update(@PathVariable Integer id, @RequestBody SmsFlashPromotion flashPromotion) throws Exception {
        return commonMethod.response(flashPromotionService.update(id , flashPromotion));
    }

    @ApiOperation("删除活动")
    @DeleteMapping(value = "/delete/{id}")
    public Object delete(@PathVariable Integer id) throws Exception {
        return commonMethod.response(flashPromotionService.delete(id));
    }

    @ApiOperation("修改上下线状态")
    @PatchMapping(value = "/update/status/{id}")
    public Object update(@PathVariable Integer id,
                         @RequestParam(name = "status") Integer status) throws Exception {
        return commonMethod.response(flashPromotionService.updateStatus(id,status));
    }

    @ApiOperation("获取活动详情")
    @GetMapping(value = "/{id}")
    public Object getItem(@PathVariable Integer id) {
        SmsFlashPromotion flashPromotion = flashPromotionService.getItem(id);
        if (flashPromotion!= null){
            return CommonResult.success(flashPromotion);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据活动名称分页查询")
    @GetMapping(value = "/list")
    public Object getItem(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        List<SmsFlashPromotion> flashPromotionList = flashPromotionService.list(keyword, pageSize, pageNum);
        if (flashPromotionList!=null){
            return CommonResult.success(flashPromotionList);
        }
        return CommonResult.failed();
    }


}
