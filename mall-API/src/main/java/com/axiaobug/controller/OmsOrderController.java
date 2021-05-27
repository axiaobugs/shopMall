package com.axiaobug.controller;

import com.axiaobug.common.CommonResult;
import com.axiaobug.dto.OmsOrderDeliveryParam;
import com.axiaobug.dto.OmsOrderQueryParam;
import com.axiaobug.pojo.oms.OmsOrder;
import com.axiaobug.repository.oms.OmsOrderRepository;
import com.axiaobug.service.impl.OmsOrderDeliveryServiceImpl;
import com.axiaobug.service.impl.OmsOrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Discription: 订单管理 controller
 * Order management controller
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@RestController
@Api(tags = "OmsOrderController")
@RequestMapping("/order")
public class OmsOrderController {

    /**/
    private final OmsOrderRepository omsOrderRepository;
    private final OmsOrderServiceImpl orderService;
    private final OmsOrderDeliveryServiceImpl omsOrderDeliveryService;

    @Autowired
    public OmsOrderController(OmsOrderRepository omsOrderRepository,
                              OmsOrderServiceImpl orderService,
                              OmsOrderDeliveryServiceImpl omsOrderDeliveryService){
        this.omsOrderRepository = omsOrderRepository;
        this.orderService=orderService;
        this.omsOrderDeliveryService = omsOrderDeliveryService;
    }


    /** 
    * @Param: queryParam define in the OmsOrderQueryParam
    * @Discription: query all order by queryParam and pageable
    * @return:
    */
    @ApiOperation("按条件查询所有订单(分页)")
    @PostMapping(value = "/list")
    public CommonResult<List<OmsOrder>> list(@RequestBody OmsOrderQueryParam queryParam){
        Integer pageNum = queryParam.getPageNum();
        Integer pageSize = queryParam.getPageSize();
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Specification<OmsOrder> specification = orderService.orderQueryParam(queryParam);
        Page<OmsOrder> orderPage = omsOrderRepository.findAll(specification,pageable);
        List<OmsOrder> orderList = orderPage.getContent();
        System.out.println(orderList.size());
        return CommonResult.success(orderList);
    }

    @ApiOperation("批量发货")
    @PostMapping(value = "/update/delivery")
    public CommonResult<?> delivery(@RequestBody List<OmsOrderDeliveryParam> deliveryParamList) {
        int size = this.omsOrderDeliveryService.delivery(deliveryParamList);
        if (size > 0){
            return CommonResult.success(size);
        }
        return CommonResult.failed();
    }
}
