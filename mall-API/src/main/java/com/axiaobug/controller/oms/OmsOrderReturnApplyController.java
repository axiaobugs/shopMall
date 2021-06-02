package com.axiaobug.controller.oms;

import com.axiaobug.common.CommonResult;
import com.axiaobug.dto.OmsOrderReturnApplyResult;
import com.axiaobug.dto.OmsReturnApplyQueryParam;
import com.axiaobug.dto.OmsUpdateStatusParam;
import com.axiaobug.pojo.oms.OmsOrderReturnApply;
import com.axiaobug.repository.oms.OmsOrderReturnApplyRepository;
import com.axiaobug.service.OmsOrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Order Return Application management
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@RestController
@Api(tags = "OmsOrderReturnApplyController")
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {

    private final OmsOrderReturnApplyService returnApplyService;
    private final OmsOrderReturnApplyRepository returnApplyRepository;

    @Autowired
    public OmsOrderReturnApplyController(OmsOrderReturnApplyService returnApplyService, OmsOrderReturnApplyRepository returnApplyRepository) {
        this.returnApplyService = returnApplyService;
        this.returnApplyRepository = returnApplyRepository;
    }

    @ApiOperation("分页查询退货申请")
    @PostMapping(value = "/list")
    public CommonResult<List<OmsOrderReturnApply>> list(@RequestBody OmsReturnApplyQueryParam queryParam) {
        Integer pageNum = queryParam.getPageNum();
        Integer pageSize = queryParam.getPageSize();
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Specification<OmsOrderReturnApply> specification = returnApplyService.list(queryParam);
        Page<OmsOrderReturnApply> orderPage = returnApplyRepository.findAll(specification, pageable);
        List<OmsOrderReturnApply> content = orderPage.getContent();
        return CommonResult.success(content);
    }

    @ApiOperation("批量删除退货申请")
    @PostMapping(value = "/delete")
    public CommonResult<Integer> delete(@RequestParam("ids") List<Integer> ids,
                               @RequestParam("note") String note) {
        int count = returnApplyService.delete(ids,note);
        if (count > 0 && count == ids.size()) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取退货申请详情")
    @GetMapping(value = "/{id}")
    public CommonResult<OmsOrderReturnApplyResult> getItem(@PathVariable Integer id){
        OmsOrderReturnApplyResult result;
        try {
            result = returnApplyService.getItem(id);
            return CommonResult.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改退货申请状态")
    @PostMapping(value = "/update/status")
    public CommonResult<Boolean> updateStatus(@RequestBody OmsUpdateStatusParam statusParam) throws Exception {
        Boolean flag = returnApplyService.updateStatus(statusParam);
        if (flag) {
            return CommonResult.success(true);
        }
        return CommonResult.failed();
    }
}
