package com.axiaobug.service.impl;

import com.axiaobug.pojo.oms.OmsCompanyAddress;
import com.axiaobug.repository.oms.OmsCompanyAddressRepository;
import com.axiaobug.service.OmsCompanyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@Service
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {


    private final OmsCompanyAddressRepository companyAddressRepository;

    @Autowired
    public OmsCompanyAddressServiceImpl(OmsCompanyAddressRepository companyAddressRepository) {
        this.companyAddressRepository = companyAddressRepository;
    }


    @Override
    public List<OmsCompanyAddress> list() {

        return companyAddressRepository.findAll();
    }
}
