package com.axiaobug.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;
import com.axiaobug.common.CommonMethod;
import com.axiaobug.pojo.oms.OmsOrderSetting;
import com.axiaobug.repository.oms.OmsOrderSettingRepository;
import com.axiaobug.service.OmsOrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {

    @Autowired
    private CommonMethod commonMethod;

    public OmsOrderSettingServiceImpl(OmsOrderSettingRepository omsOrderSettingRepository) {
        this.omsOrderSettingRepository = omsOrderSettingRepository;
    }

    private final OmsOrderSettingRepository omsOrderSettingRepository;



    @Override
    public OmsOrderSetting getItem(Integer id) throws Exception {
        if (this.omsOrderSettingRepository.findById(id).isPresent()) {
            return this.omsOrderSettingRepository.findById(id).get();
        }
        throw new Exception("Can't find setting id");
    }

    @Override
    public Boolean update(OmsOrderSetting orderSetting) throws Exception {
        if (this.omsOrderSettingRepository.findById(orderSetting.getId()).isPresent()){
            OmsOrderSetting setting = this.omsOrderSettingRepository.findById(orderSetting.getId()).get();
            Boolean isNull = commonMethod.setParamToTarget(orderSetting, setting);
            if(!isNull){
                try {
                    this.omsOrderSettingRepository.save(setting);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

        }
        throw new Exception("Can't find the setting_id in database");
    }
}
