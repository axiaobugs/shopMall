package com.axiaobug.repository.oms;

import com.axiaobug.pojo.oms.OmsOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface OmsOrderItemsRepository extends JpaRepository<OmsOrderItem,Integer>, JpaSpecificationExecutor<OmsOrderItem> {

    List<OmsOrderItem> findByOrderId(Integer id);
}
