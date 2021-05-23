package com.axiaobug.repository.pms;

import com.axiaobug.pojo.pms.PmsProductFullReduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface PmsProductFullReductionRepository
        extends JpaRepository<PmsProductFullReduction,Integer>,
        JpaSpecificationExecutor<PmsProductFullReduction> {
}
