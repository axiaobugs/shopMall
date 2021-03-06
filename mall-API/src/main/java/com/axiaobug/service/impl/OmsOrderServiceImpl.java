package com.axiaobug.service.impl;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.dto.OmsMoneyInfoParam;
import com.axiaobug.dto.OmsOrderDetail;
import com.axiaobug.dto.OmsOrderQueryParam;
import com.axiaobug.dto.OmsReceiverInfoParam;
import com.axiaobug.pojo.oms.OmsOrder;
import com.axiaobug.pojo.oms.OmsOrderItem;
import com.axiaobug.pojo.oms.OmsOrderOperateHistory;
import com.axiaobug.repository.oms.OmsOrderItemsRepository;
import com.axiaobug.repository.oms.OmsOrderOperateHistoryRepository;
import com.axiaobug.repository.oms.OmsOrderRepository;
import com.axiaobug.service.OmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@Service
public class OmsOrderServiceImpl implements OmsOrderService {

    private final CommonMethod commonMethod;
    private final OmsOrderRepository omsOrderRepository;
    private final OmsOrderOperateHistoryRepository omsOrderOperateHistoryRepository;
    private final OmsOrderItemsRepository omsOrderItemsRepository;

    @Autowired
    public OmsOrderServiceImpl(CommonMethod commonMethod, OmsOrderRepository omsOrderRepository,
                               OmsOrderOperateHistoryRepository omsOrderOperateHistoryRepository,
                               OmsOrderItemsRepository omsOrderItemsRepository) {
        this.commonMethod = commonMethod;
        this.omsOrderRepository = omsOrderRepository;
        this.omsOrderOperateHistoryRepository = omsOrderOperateHistoryRepository;
        this.omsOrderItemsRepository = omsOrderItemsRepository;
    }


    public OmsOrderOperateHistory createHistory(int id,String status,String note,String name,Date time){
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setOperateMan(name);
        if ("close".equals(status)){
            history.setOrderStatus(4);
            history.setNote("close order"+note);
        }else if("delete".equals(status)) {
            history.setOrderStatus(5);
            history.setNote("delete order"+note);
        }
        history.setCreateTime(time);
        return history;
    }

    public OmsOrder createOrderObject(int id,String status,Date time){
        OmsOrder order = omsOrderRepository.findById(id).get();
        if ("close".equals(status)){
            order.setStatus(4);
        }else if("delete".equals(status)){
            order.setStatus(5);
            order.setDeleteStatus(1);
        }
        order.setDeliveryTime(time);
        return order;
    }


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
                commonMethod.timeRangeMatch(predicates,
                        queryParam.getCreateTime(),
                        root,
                        criteriaQuery,
                        criteriaBuilder,
                        "createTime");
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }


    /**
    * @Discription:
    * @Param: ids, note
    * @return:
    * TODO: could add OperateMan as other param
    */
    @Override
    public int close(List<Integer> ids, String note) {
        AtomicInteger i= new AtomicInteger();
        Date time = new Date();

        ids.forEach(id -> {
            OmsOrderOperateHistory history =createHistory(id,"close","????????????","Admin",time);
            OmsOrder order = createOrderObject(id,"close",time);
            try {
                this.omsOrderRepository.save(order);
                this.omsOrderOperateHistoryRepository.save(history);
                i.getAndIncrement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return i.get();
    }

    @Override
    public int delete(List<Integer> ids) {

        AtomicInteger i= new AtomicInteger();
        Date time = new Date();

        ids.forEach(id -> {
            OmsOrderOperateHistory history = createHistory(id,"delete","????????????","Admin",time);

            OmsOrder order = createOrderObject(id,"delete",time);
            try {
                this.omsOrderRepository.save(order);
                this.omsOrderOperateHistoryRepository.save(history);
                i.getAndIncrement();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        return i.get();
    }

    @Override
    public OmsOrderDetail detail(Integer id) {
        List<OmsOrderItem> orderItems = omsOrderItemsRepository.findByOrderId(id);
        List<OmsOrderOperateHistory> operateHistories = omsOrderOperateHistoryRepository.findAllById(Collections.singleton(id));
        OmsOrder omsOrder = omsOrderRepository.findById(id).orElse(null);
        return new OmsOrderDetail(omsOrder,orderItems,operateHistories);
    }

    @Override
    public boolean updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) throws Exception {
        if (omsOrderRepository.findById(receiverInfoParam.getOrderId()).isPresent()){
            OmsOrder order = omsOrderRepository.findById(receiverInfoParam.getOrderId()).get();
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(receiverInfoParam.getOrderId());
            if (receiverInfoParam.getReceiverName()!= null){
                order.setReceiverName(receiverInfoParam.getReceiverName());
            }
            if (receiverInfoParam.getReceiverPhone() != null){
                order.setReceiverPhone(receiverInfoParam.getReceiverPhone());
            }
            if (receiverInfoParam.getReceiverPostCode()!= null){
                order.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
            }
            if (receiverInfoParam.getReceiverDetailAddress()!= null){
                order.setReceiverAddress(receiverInfoParam.getReceiverDetailAddress());
            }
            if (receiverInfoParam.getReceiverProvince()!= null){
                order.setReceiverProvince(receiverInfoParam.getReceiverProvince());
            }
            if (receiverInfoParam.getReceiverCity()!=null){
                order.setReceiverCity(receiverInfoParam.getReceiverCity());
            }
            if (receiverInfoParam.getReceiverRegion()!=null){
                order.setReceiverSuburb(receiverInfoParam.getReceiverRegion());
            }
            if (receiverInfoParam.getStatus()!=null){
                order.setStatus(receiverInfoParam.getStatus());
                history.setOrderStatus(receiverInfoParam.getStatus());
            }
            history.setCreateTime(new Date());
            history.setNote("?????????????????????");
            history.setOperateMan("Admin");
            try {
                omsOrderRepository.save(order);
                omsOrderOperateHistoryRepository.save(history);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("request param have wrong format or wrong");
    }


    @Override
    public boolean updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam) throws Exception {
        if (omsOrderRepository.findById(moneyInfoParam.getOrderId()).isPresent()){
            OmsOrder order = omsOrderRepository.findById(moneyInfoParam.getOrderId()).get();
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(moneyInfoParam.getOrderId());
            if (moneyInfoParam.getFreightAmount()!=null){
                order.setFreightAmount(moneyInfoParam.getFreightAmount());
            }
            if (moneyInfoParam.getDiscountAmount()!=null){
                order.setDiscountAmount(moneyInfoParam.getDiscountAmount());
            }
            if (moneyInfoParam.getStatus()!=null){
                order.setStatus(moneyInfoParam.getStatus());
                history.setOrderStatus(moneyInfoParam.getStatus());
            }
            history.setCreateTime(new Date());
            history.setNote("????????????????????????");
            history.setOperateMan("Admin");

            try {
                omsOrderRepository.save(order);
                omsOrderOperateHistoryRepository.save(history);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("request param have wrong format or wrong");
    }

    @Override
    public boolean updateNote(Integer id, String note, Integer status) throws Exception {
        if (omsOrderRepository.findById(id).isPresent()){
            OmsOrder order = omsOrderRepository.findById(id).get();
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(id);
            if (note !=null){
                order.setNote(note);
                history.setNote("??????????????????: "+note);
            }
            if (status != null){
                order.setStatus(status);
                history.setOrderStatus(status);
            }
            history.setCreateTime(new Date());
            history.setOperateMan("Admin");

            try {
                omsOrderRepository.save(order);
                omsOrderOperateHistoryRepository.save(history);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("request param have wrong format or wrong");
    }
}
