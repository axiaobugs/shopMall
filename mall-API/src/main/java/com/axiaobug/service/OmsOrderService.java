package com.axiaobug.service;

import com.axiaobug.dto.*;
import com.axiaobug.pojo.oms.OmsOrder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface OmsOrderService {
    /**
    * @Param: queryParam
    * @return:
    * @Discription:
    */
    Specification<OmsOrder> orderQueryParam(OmsOrderQueryParam queryParam);

    /**
    * close order in bulk
    * @Param:  ids, note
    * @return: num of size of close in this session
    */
    @Transactional(rollbackFor = Exception.class)
    int close(List<Integer> ids, String note);

    /**
    * delete order in bulk.
    * @Param: ids
    * @return: num of size of close in this session
    */
    int delete(List<Integer> ids);

    /**
    * get order detail include orderItems and orderOperateHistory (all).
    * @Param: id == orderID
    * @return: OmsOrderDetail
    */
    OmsOrderDetail detail(Integer id);

    /**
    * Edit reviver Info include name address orderStatus etc.
    * @Param: receiverInfoParam
    * @return:
    */
    @Transactional(rollbackFor = Exception.class)
    boolean updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) throws Exception;

    /**
     * Edit the order fee info.
     * @Param: moneyInfoParam
     * @return:
     */
    @Transactional(rollbackFor = Exception.class)
    boolean updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam) throws Exception;

    /**
    * Edit the order note.
    * @Param: id, note, status
    * @return:
    */
    @Transactional(rollbackFor = Exception.class)
    boolean updateNote(Integer id, String note, Integer status) throws Exception;
}
