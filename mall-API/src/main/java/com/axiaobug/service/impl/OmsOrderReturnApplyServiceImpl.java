package com.axiaobug.service.impl;

import cn.hutool.core.date.DateUtil;
import com.axiaobug.dto.OmsOrderReturnApplyResult;
import com.axiaobug.dto.OmsReturnApplyQueryParam;
import com.axiaobug.dto.OmsUpdateStatusParam;
import com.axiaobug.pojo.oms.OmsOrderReturnApply;
import com.axiaobug.repository.oms.OmsOrderReturnApplyRepository;
import com.axiaobug.service.OmsOrderReturnApplyService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@Service
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {

    private final OmsOrderReturnApplyRepository returnApplyRepository;

    public OmsOrderReturnApplyServiceImpl(OmsOrderReturnApplyRepository returnApplyRepository) {
        this.returnApplyRepository = returnApplyRepository;
    }


    @Override
    public Specification<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam) {
        return  (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // Exact match
            if (queryParam.getId()!=null){
                predicates.add(criteriaBuilder.equal(root.get("orderId"),queryParam.getId()));
            }
            if (queryParam.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), queryParam.getStatus()));
            }
            if (queryParam.getReceiverName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("receiverName"), queryParam.getReceiverName()));
            }
            if (queryParam.getReceiverPhone() != null) {
                predicates.add(criteriaBuilder.equal(root.get("receiverPhone"), queryParam.getReceiverPhone()));
            }
            if (queryParam.getHandleMan() != null) {
                predicates.add(criteriaBuilder.equal(root.get("handleMan"), queryParam.getHandleMan()));
            }
            // Range match
            if (queryParam.getCreateTime() != null) {
                Date queryTime = queryParam.getCreateTime();
                Date beginOfDay = DateUtil.beginOfDay(queryTime);
                Date endOfDay = DateUtil.endOfDay(queryTime);
                predicates.add(criteriaBuilder.between(root.get("createTime").as(String.class), beginOfDay.toString(), endOfDay.toString()));
            }
            if (queryParam.getHandleTime() != null) {
                Date queryTime = queryParam.getHandleTime();
                Date beginOfDay = DateUtil.beginOfDay(queryTime);
                Date endOfDay = DateUtil.endOfDay(queryTime);
                predicates.add(criteriaBuilder.between(root.get("handleTime").as(String.class), beginOfDay.toString(), endOfDay.toString()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    @Override
    public int delete(List<Long> ids) {
        return 0;
    }

    @Override
    public int updateStatus(Long id, OmsUpdateStatusParam statusParam) {
        return 0;
    }

    @Override
    public OmsOrderReturnApplyResult getItem(Long id) {
        return null;
    }
}
