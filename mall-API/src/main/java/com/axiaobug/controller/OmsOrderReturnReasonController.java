package com.axiaobug.controller;

import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.oms.OmsOrderReturnReason;
import com.axiaobug.repository.oms.OmsOrderReturnReasonRepository;
import com.axiaobug.service.OmsOrderReturnReasonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "OmsOrderReturnReasonController")
@RequestMapping("/returnReason")
public class OmsOrderReturnReasonController {
    @Resource
    private OmsOrderReturnReasonService orderReturnReasonService;

    @Resource
    private OmsOrderReturnReasonRepository reasonRepository;

    @ApiOperation("添加退货原因")
    @PostMapping(value = "/create")
    public CommonResult<Boolean> create(@RequestBody OmsOrderReturnReason returnReason) {
        Boolean flag = orderReturnReasonService.create(returnReason);
        if (flag) {
            return CommonResult.success(true);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改退货原因")
    @PatchMapping(value = "/update")
    public CommonResult<Boolean> update(@RequestBody OmsOrderReturnReason returnReason) throws Exception {
        Boolean flag = orderReturnReasonService.update(returnReason);
        if (flag) {
            return CommonResult.success(true);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除退货原因")
    @DeleteMapping(value = "/delete")
    public CommonResult<Integer> delete(@RequestParam("ids") List<Integer> ids) throws Exception {
        int count = orderReturnReasonService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分页查询全部退货原因")
    @GetMapping(value = "/list")
    public CommonResult<List<OmsOrderReturnReason>> list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                               @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        try {
            Page<OmsOrderReturnReason> reasonPage = reasonRepository.findAll(pageable);
            return CommonResult.success(reasonPage.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
    }

    @ApiOperation("获取单个退货原因详情信息")
    @GetMapping(value = "/{id}")
    public CommonResult<OmsOrderReturnReason> getItem(@PathVariable Integer id) {
        boolean present = reasonRepository.findById(id).isPresent();
        if (present){
            OmsOrderReturnReason reason = reasonRepository.findById(id).get();
            return CommonResult.success(reason);
        }
        return CommonResult.failed("Can't find reason detail");
    }

    @ApiOperation("批量修改退货原因启用状态")
    @PostMapping(value = "/update/status")
    public CommonResult<Integer> updateStatus(@RequestParam(value = "status") Integer status,
                                     @RequestParam(value = "ids") List<Integer> ids) throws Exception {
        int count = orderReturnReasonService.updateStatus(ids, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
