package com.axiaobug.service.impl;

import com.axiaobug.dto.OmsOrderDeliveryParam;
import com.axiaobug.pojo.oms.OmsOrder;
import com.axiaobug.pojo.oms.OmsOrderOperateHistory;
import com.axiaobug.repository.oms.OmsOrderOperateHistoryRepository;
import com.axiaobug.repository.oms.OmsOrderRepository;
import com.axiaobug.service.OmsOrderDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@Service
public class OmsOrderDeliveryServiceImpl implements OmsOrderDeliveryService {

    private final OmsOrderRepository omsOrderRepository;
    private final OmsOrderOperateHistoryRepository omsOrderOperateHistoryRepository;

    @Autowired
    public OmsOrderDeliveryServiceImpl(OmsOrderRepository omsOrderRepository,
                                       OmsOrderOperateHistoryRepository omsOrderOperateHistoryRepository){
        this.omsOrderRepository = omsOrderRepository;
        this.omsOrderOperateHistoryRepository = omsOrderOperateHistoryRepository;
    }
    /**
     * @Param: [deliveryParamList]
     */
    @Override
    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {

        int size = deliveryParamList.size();

        deliveryParamList.forEach(omsOrderDeliveryParam -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            Date time = new Date();
            history.setOrderId(omsOrderDeliveryParam.getId());
            history.setOperateMan("Admin");
            history.setOrderStatus(2);
            history.setNote("Dispatch done");
            history.setCreateTime(time);

            OmsOrder order = omsOrderRepository.findById(omsOrderDeliveryParam.getId()).get();
            order.setStatus(2);
            order.setDeliveryTime(time);
            order.setDeliveryCompany(omsOrderDeliveryParam.getDeliveryCompany());
            order.setDeliverySerialNum(omsOrderDeliveryParam.getDeliverySerialNum());
            try {
                this.omsOrderRepository.save(order);
                this.omsOrderOperateHistoryRepository.save(history);
            } catch (Exception e) {
                e.printStackTrace();

            }
        });
        return size;
    }



}
