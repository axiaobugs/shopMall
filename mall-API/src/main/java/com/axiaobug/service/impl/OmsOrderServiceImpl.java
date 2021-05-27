package com.axiaobug.service.impl;

import cn.hutool.core.date.DateUtil;
import com.axiaobug.dto.OmsOrderQueryParam;
import com.axiaobug.pojo.oms.OmsOrder;
import com.axiaobug.repository.oms.OmsOrderOperateHistoryRepository;
import com.axiaobug.repository.oms.OmsOrderRepository;
import com.axiaobug.service.OmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@Service
public class OmsOrderServiceImpl implements OmsOrderService {

    @Override
    public Specification<OmsOrder> orderQueryParam(OmsOrderQueryParam queryParam) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (queryParam.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), queryParam.getStatus()));
            }
            if (queryParam.getOrderType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("orderType"), queryParam.getOrderType()));
            }
            if (queryParam.getReceiverName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("receiverName"), queryParam.getReceiverName()));
            }
            if (queryParam.getOrderSerialNum() != null) {
                predicates.add(criteriaBuilder.equal(root.get("orderSerialNum"), queryParam.getOrderSerialNum()));
            }
            if (queryParam.getReceiverPhone() != null) {
                predicates.add(criteriaBuilder.equal(root.get("receiverPhone"), queryParam.getReceiverPhone()));
            }
            if (queryParam.getSourceType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("sourceType"), queryParam.getSourceType()));
            }
            // time range search
            if (queryParam.getCreateTime() != null) {
                Date queryTime = queryParam.getCreateTime();
                Date beginOfDay = DateUtil.beginOfDay(queryTime);
                Date endOfDay = DateUtil.endOfDay(queryTime);
                predicates.add(criteriaBuilder.between(root.get("createTime"), beginOfDay, endOfDay));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }


}
