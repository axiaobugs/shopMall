package com.axiaobug.repository.pms;

import com.axiaobug.pojo.pms.PmsProductOperateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface PmsProductOperateLogRepository extends JpaRepository<PmsProductOperateLog,Integer>, JpaSpecificationExecutor<PmsProductOperateLog> {
}
