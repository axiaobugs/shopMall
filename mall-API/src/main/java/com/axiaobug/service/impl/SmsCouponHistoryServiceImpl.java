package com.axiaobug.service.impl;

import com.axiaobug.pojo.sms.SmsCouponHistory;
import com.axiaobug.repository.sms.SmsCouponHistoryRepository;
import com.axiaobug.service.SmsCouponHistoryService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class SmsCouponHistoryServiceImpl implements SmsCouponHistoryService {
    @Resource
    private SmsCouponHistoryRepository historyRepository;


    @Override
    public List<SmsCouponHistory> list(Integer couponId, Integer useStatus, String orderSerialNum, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        SmsCouponHistory history = new SmsCouponHistory();
        history.setCouponId(couponId);
        history.setUseStatus(useStatus);
        history.setOrderSerialNum(orderSerialNum);
        Example<SmsCouponHistory> example = Example.of(history);
        return historyRepository.findAll(example,pageable).getContent();
    }
}
