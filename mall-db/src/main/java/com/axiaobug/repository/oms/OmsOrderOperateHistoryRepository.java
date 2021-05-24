package com.axiaobug.repository.oms;

import com.axiaobug.pojo.oms.OmsOrderOperateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface OmsOrderOperateHistoryRepository
        extends JpaRepository<OmsOrderOperateHistory,Integer>,
        JpaSpecificationExecutor<OmsOrderOperateHistory> {
}
