package com.axiaobug.service.impl;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.dto.OmsOrderReturnApplyResult;
import com.axiaobug.dto.OmsReturnApplyQueryParam;
import com.axiaobug.dto.OmsUpdateStatusParam;
import com.axiaobug.pojo.oms.OmsCompanyAddress;
import com.axiaobug.pojo.oms.OmsOrderOperateHistory;
import com.axiaobug.pojo.oms.OmsOrderReturnApply;
import com.axiaobug.repository.oms.OmsCompanyAddressRepository;
import com.axiaobug.repository.oms.OmsOrderOperateHistoryRepository;
import com.axiaobug.repository.oms.OmsOrderReturnApplyRepository;
import com.axiaobug.service.OmsOrderReturnApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@Service
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {

    private final CommonMethod commonMethod;
    private final OmsOrderReturnApplyRepository returnApplyRepository;
    private final OmsOrderOperateHistoryRepository historyRepository;
    private final OmsCompanyAddressRepository addressRepository;

    @Autowired
    public OmsOrderReturnApplyServiceImpl(CommonMethod commonMethod, OmsOrderReturnApplyRepository returnApplyRepository, OmsOrderOperateHistoryRepository historyRepository, OmsCompanyAddressRepository addressRepository) {
        this.commonMethod = commonMethod;
        this.returnApplyRepository = returnApplyRepository;
        this.historyRepository = historyRepository;
        this.addressRepository = addressRepository;
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
                commonMethod.timeRangeMatch(predicates,
                        queryParam.getCreateTime(),
                        root,
                        criteriaQuery,
                        criteriaBuilder,
                        "createTime");
            }
            if (queryParam.getHandleTime() != null) {
                commonMethod.timeRangeMatch(predicates,
                        queryParam.getCreateTime(),
                        root,
                        criteriaQuery,
                        criteriaBuilder,
                        "handleTime");
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    @Override
    public int delete(List<Integer> ids,String note) {
        AtomicInteger i= new AtomicInteger();
        Date time = new Date();

        ids.forEach(id ->{
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            if (returnApplyRepository.findById(id).isPresent()){
                OmsOrderReturnApply returnApply = returnApplyRepository.findById(id).get();
                returnApply.setStatus(3);
                history.setOrderId(returnApply.getOrderId());
                history.setNote("退货申请删除: "+note);
                history.setOperateMan("Admin");
                history.setCreateTime(time);
                history.setOrderStatus(7);
                try {
                    returnApplyRepository.saveAndFlush(returnApply);
                    historyRepository.save(history);
                    i.getAndIncrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return i.get();
    }

    @Override
    public Boolean updateStatus(OmsUpdateStatusParam statusParam) throws Exception {
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        Integer id = statusParam.getId();
        if (returnApplyRepository.findById(id).isPresent()) {
            OmsOrderReturnApply returnApply = returnApplyRepository.findById(id).get();
            history.setOrderId(id);
            if (statusParam.getCompanyAddressId() != null) {
                returnApply.setCompanyAddressId(statusParam.getCompanyAddressId());
            }
            if (statusParam.getReturnAmount()!= null){
                returnApply.setReturnAmount(statusParam.getReturnAmount());
            }
            if (statusParam.getHandleNote()!= null){
                returnApply.setHandleNote(statusParam.getHandleNote());
            }
            if (statusParam.getHandleMan()!=null){
                returnApply.setHandleMan(statusParam.getHandleMan());
                history.setOperateMan(statusParam.getHandleMan());
            }
            if (statusParam.getReceiveNote()!=null){
                returnApply.setReceiveNote(statusParam.getReceiveNote());
                history.setNote("退货订单状态更新"+statusParam.getReceiveNote());
            }
            if (statusParam.getReceiveMan()!=null){
                returnApply.setReceiveMan(statusParam.getReceiveMan());
            }
            if (statusParam.getStatus()!=null){
                returnApply.setStatus(statusParam.getStatus());
                history.setOrderStatus(statusParam.getStatus());
            }

            try {
                returnApplyRepository.save(returnApply);
                historyRepository.save(history);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("");
    }

    @Override
    public OmsOrderReturnApplyResult getItem(Integer id) throws Exception {
        OmsOrderReturnApplyResult result = new OmsOrderReturnApplyResult();
        if (returnApplyRepository.findById(id).isPresent()) {
            OmsOrderReturnApply returnApply = returnApplyRepository.findById(id).get();
            result.setReturnApply(returnApply);
            Integer companyAddressId = returnApply.getCompanyAddressId();
            if (companyAddressId != null) {
                if (addressRepository.findById(companyAddressId).isPresent()) {
                    OmsCompanyAddress address = addressRepository.findById(companyAddressId).get();
                    result.setCompanyAddress(address);
                }
            }
            return  result;
        }
        throw new Exception("Wrong");
    }
}
