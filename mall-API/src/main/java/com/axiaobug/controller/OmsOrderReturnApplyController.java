package com.axiaobug.controller;

import com.axiaobug.common.CommonResult;
import com.axiaobug.dto.OmsReturnApplyQueryParam;
import com.axiaobug.pojo.oms.OmsOrderReturnApply;
import com.axiaobug.repository.oms.OmsOrderReturnApplyRepository;
import com.axiaobug.service.OmsOrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
}
