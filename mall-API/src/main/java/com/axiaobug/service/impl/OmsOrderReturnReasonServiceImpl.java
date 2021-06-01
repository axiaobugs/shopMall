package com.axiaobug.service.impl;

import com.axiaobug.pojo.oms.OmsOrderReturnReason;
import com.axiaobug.repository.oms.OmsOrderReturnReasonRepository;
import com.axiaobug.service.OmsOrderReturnReasonService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Order return reason management
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class OmsOrderReturnReasonServiceImpl implements OmsOrderReturnReasonService {

    private final OmsOrderReturnReasonRepository omsOrderReturnReasonRepository;

    public OmsOrderReturnReasonServiceImpl(OmsOrderReturnReasonRepository omsOrderReturnReasonRepository) {
        this.omsOrderReturnReasonRepository = omsOrderReturnReasonRepository;
    }


    @Override
    public Boolean create(OmsOrderReturnReason returnReason) {
        returnReason.setCreateTime(new Date());
        try {
            this.omsOrderReturnReasonRepository.save(returnReason);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(OmsOrderReturnReason returnReason) throws Exception {
        if (returnReason.getId()!= null){
            this.omsOrderReturnReasonRepository.save(returnReason);
            return true;
        }
        throw new Exception("Reason (can not find reason ID) not in our database");
    }

    @Override
    public Integer delete(List<Integer> ids) throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();
        ids.forEach(id->{
            boolean present = this.omsOrderReturnReasonRepository.findById(id).isPresent();
            try {
                if (present){
                    this.omsOrderReturnReasonRepository.deleteById(id);
                    atomicInteger.getAndIncrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        if (atomicInteger.get() == ids.size()){
            return atomicInteger.get();
        }
        throw new Exception("Failure to delete return_reason in our database");
    }

    @Override
    public List<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum) {
        return null;
    }


    @Override
    public Integer updateStatus(List<Integer> ids, Integer status) throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();
        if (!ids.isEmpty()){
            ids.forEach(id->{
                boolean present = omsOrderReturnReasonRepository.findById(id).isPresent();
                if (present){
                    OmsOrderReturnReason returnReason = omsOrderReturnReasonRepository.findById(id).get();
                    returnReason.setStatus(status);
                    try {
                        omsOrderReturnReasonRepository.save(returnReason);
                        atomicInteger.getAndIncrement();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
        }
        if (atomicInteger.get()==ids.size()){
            return atomicInteger.get();
        }
        throw new Exception("update rollback because length of success are not equal list size");
    }

    @Override
    public OmsOrderReturnReason getItem(Integer id) {
        return null;
    }
}
