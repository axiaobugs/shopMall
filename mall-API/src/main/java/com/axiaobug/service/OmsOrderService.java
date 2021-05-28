package com.axiaobug.service;

import com.axiaobug.dto.OmsOrderDeliveryParam;
import com.axiaobug.dto.OmsOrderQueryParam;
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
    * @Discription: close order in bulk
    * @Param:  ids, note
    * @return: num of size of close in this session
    */
    @Transactional(rollbackFor = Exception.class)
    int close(List<Integer> ids, String note);
}
