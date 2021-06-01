package com.axiaobug.service.impl;

import com.axiaobug.pojo.oms.OmsOrderSetting;
import com.axiaobug.repository.oms.OmsOrderSettingRepository;
import com.axiaobug.service.OmsOrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {

    @Autowired
    private OmsOrderSettingRepository omsOrderSettingRepository;



    @Override
    public OmsOrderSetting getItem(Integer id) throws Exception {
        if (omsOrderSettingRepository.findById(id).isPresent()) {
            return omsOrderSettingRepository.findById(id).get();
        }
        throw new Exception("Can't find setting id");
    }

    @Override
    public Boolean update(OmsOrderSetting orderSetting) throws Exception {
        if (omsOrderSettingRepository.findById(orderSetting.getId()).isPresent()){
            OmsOrderSetting setting = omsOrderSettingRepository.findById(orderSetting.getId()).get();
            if (orderSetting.getCommentOverTime()!=null){
                setting.setCommentOverTime(orderSetting.getCommentOverTime());
            }
            if (orderSetting.getConfirmOverTime()!=null){
                setting.setConfirmOverTime(orderSetting.getConfirmOverTime());
            }
            if (orderSetting.getFlashOrderOverTime()!=null){
                setting.setFlashOrderOverTime(orderSetting.getFlashOrderOverTime());
            }
            if (orderSetting.getNormalOrderOverTime()!=null){
                setting.setNormalOrderOverTime(orderSetting.getNormalOrderOverTime());
            }
            if (orderSetting.getFinishOverTime()!=null){
                setting.setFinishOverTime(orderSetting.getFinishOverTime());
            }

            try {
                omsOrderSettingRepository.save(setting);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("Can't find the setting_id in database");
    }
}
