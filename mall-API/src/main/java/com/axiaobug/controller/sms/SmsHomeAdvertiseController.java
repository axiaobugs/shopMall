package com.axiaobug.controller.sms;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.sms.SmsHomeAdvertise;
import com.axiaobug.service.SmsHomeAdvertiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * management of index Carousel controller
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "SmsHomeAdvertiseController")
@RequestMapping("/home/advertise")
public class SmsHomeAdvertiseController {
    @Resource
    private SmsHomeAdvertiseService advertiseService;

    @Resource
    private CommonMethod commonMethod;


    @ApiOperation("添加广告")
    @PostMapping(value = "/create")
    public CommonResult<Boolean> create(@RequestBody SmsHomeAdvertise advertise) throws Exception {
        return commonMethod.response(advertiseService.create(advertise));
    }

    @ApiOperation("批量删除广告")
    @DeleteMapping(value = "/delete/")
    public CommonResult<Boolean> delete(@RequestParam("ids") List<Integer> ids) throws Exception {
        return commonMethod.response(advertiseService.delete(ids));
    }

    @ApiOperation("修改上下线状态")
    @PatchMapping(value = "/update/status/{id}")
    public CommonResult<Boolean> updateStatus(@PathVariable Integer id, Integer status) throws Exception {
        return commonMethod.response(advertiseService.updateStatus(id,status));
    }

    @ApiOperation("获取广告详情")
    @GetMapping(value = "/{id}")
    public CommonResult<SmsHomeAdvertise> getItem(@PathVariable Integer id) throws Exception {
        SmsHomeAdvertise advertise = advertiseService.getItem(id);
        if(!commonMethod.isEmptyObject(advertise)){
            return CommonResult.success(advertise);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改广告")
    @PatchMapping(value = "/update/{id}")
    public CommonResult<Boolean> update(@PathVariable Integer id, @RequestBody SmsHomeAdvertise advertise) throws Exception {
        return commonMethod.response(advertiseService.update(id,advertise));
    }

    @ApiOperation("分页查询广告")
    @GetMapping(value = "/list")
    public CommonResult<List<SmsHomeAdvertise>> list(@RequestParam(value = "name", required = false) String name,
                                                           @RequestParam(value = "type", required = false) Integer type,
                                                           @RequestParam(value = "endTime", required = false) Date endTime,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        List<SmsHomeAdvertise> advertiseList = advertiseService.list(name, type, endTime, pageSize, pageNum);
        if (advertiseList!=null && advertiseList.size()>0){
            return CommonResult.success(advertiseList);
        }
        return CommonResult.failed();
    }


}
