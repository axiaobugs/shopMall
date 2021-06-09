package com.axiaobug.controller.sms;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.sms.SmsFlashPromotionSession;
import com.axiaobug.service.SmsFlashPromotionSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "SmsFlashPromotionSessionController")
@RequestMapping("/flashSession")
public class SmsFlashPromotionSessionController {

    @Resource
    private SmsFlashPromotionSessionService sessionService;

    @Autowired
    private CommonMethod commonMethod;

    @ApiOperation("添加场次")
    @PostMapping(value = "/create")
    public CommonResult<Boolean> create(@RequestBody SmsFlashPromotionSession promotionSession) {
        return commonMethod.response(sessionService.create(promotionSession));
    }

    @ApiOperation("修改场次")
    @PatchMapping(value = "/update/{id}")
    public CommonResult<Boolean> update(@PathVariable Integer id, @RequestBody SmsFlashPromotionSession promotionSession) throws Exception {
        return commonMethod.response(sessionService.update(id,promotionSession));
    }

    @ApiOperation("修改启用状态")
    @PatchMapping(value = "/update/status/{id}")
    public CommonResult<Boolean> updateStatus(@PathVariable Integer id,
                                              @RequestParam(name = "status") Integer status) throws Exception {
        return commonMethod.response(sessionService.updateStatus(id,status));
    }

    @ApiOperation("删除场次")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult<Boolean> delete(@PathVariable Integer id) throws Exception {
        return commonMethod.response(sessionService.delete(id));
    }

    @ApiOperation("获取场次详情")
    @GetMapping(value = "/{id}")
    public CommonResult<SmsFlashPromotionSession> getItem(@PathVariable Integer id) {
        SmsFlashPromotionSession promotionSession = sessionService.getItem(id);
        if (promotionSession!=null){
            return CommonResult.success(promotionSession);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取全部场次")
    @GetMapping(value = "/list")
    public CommonResult<List<SmsFlashPromotionSession>> list() {
        List<SmsFlashPromotionSession> promotionSessionList = sessionService.list();
        if (!promotionSessionList.isEmpty()){
            return CommonResult.success(promotionSessionList);
        }
        return CommonResult.failed();
    }

//    @ApiOperation("获取全部可选场次及其数量")
//    @GetMapping(value = "/selectList")
//    public CommonResult<List<SmsFlashPromotionSessionDetail>> selectList(Integer flashPromotionId) {
//        List<SmsFlashPromotionSessionDetail> promotionSessionList = sessionService.selectList(flashPromotionId);
//        return CommonResult.success(promotionSessionList);
//    }

}
