package com.axiaobug.service.impl;

import cn.hutool.core.date.DateUtil;
import com.axiaobug.dto.OmsOrderDetail;
import com.axiaobug.dto.OmsOrderQueryParam;
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

    private final OmsOrderRepository omsOrderRepository;
    private final OmsOrderOperateHistoryRepository omsOrderOperateHistoryRepository;
    private final OmsOrderItemsRepository omsOrderItemsRepository;

    @Autowired
    public OmsOrderServiceImpl(OmsOrderRepository omsOrderRepository,
                               OmsOrderOperateHistoryRepository omsOrderOperateHistoryRepository,
                               OmsOrderItemsRepository omsOrderItemsRepository) {
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
                Date queryTime = queryParam.getCreateTime();
                Date beginOfDay = DateUtil.beginOfDay(queryTime);
                Date endOfDay = DateUtil.endOfDay(queryTime);
                predicates.add(criteriaBuilder.between(root.get("createTime"), beginOfDay, endOfDay));
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
            OmsOrderOperateHistory history =createHistory(id,"close","关闭订单","Admin",time);
            OmsOrder order = createOrderObject(id,"close",time);
            try {
                this.omsOrderRepository.save(order);
                this.omsOrderOperateHistoryRepository.save(history);
                i.getAndIncrement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return i.incrementAndGet();
    }

    @Override
    public int delete(List<Integer> ids) {

        AtomicInteger i= new AtomicInteger();
        Date time = new Date();

        ids.forEach(id -> {
            OmsOrderOperateHistory history = createHistory(id,"delete","删除订单","Admin",time);

            OmsOrder order = createOrderObject(id,"delete",time);
            try {
                this.omsOrderRepository.save(order);
                this.omsOrderOperateHistoryRepository.save(history);
                i.getAndIncrement();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        return i.incrementAndGet();
    }

    @Override
    public OmsOrderDetail detail(Integer id) {
        List<OmsOrderItem> orderItems = omsOrderItemsRepository.findByOrderId(id);
        List<OmsOrderOperateHistory> operateHistories = omsOrderOperateHistoryRepository.findAllById(Collections.singleton(id));
        OmsOrder omsOrder = omsOrderRepository.findById(id).orElse(null);
        return new OmsOrderDetail(omsOrder,orderItems,operateHistories);
    }
}
