package com.axiaobug.controller.oms;

import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.oms.OmsCompanyAddress;
import com.axiaobug.repository.oms.OmsCompanyAddressRepository;
import com.axiaobug.service.OmsCompanyAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Receiver Address management
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */

@RestController
@Api(tags = "OmsCompanyAddressController")
@RequestMapping("/companyAddress")
public class OmsCompanyAddressController {

    private final OmsCompanyAddressRepository companyAddressRepository;

    @Autowired
    public OmsCompanyAddressController( OmsCompanyAddressRepository companyAddressRepository) {
        this.companyAddressRepository = companyAddressRepository;
    }




    @ApiOperation("获取所有收货地址")
    @GetMapping(value = "/list")
    public CommonResult<List<OmsCompanyAddress>> list() {
        return CommonResult.success(companyAddressRepository.findAll());
    }


}
