package com.axiaobug.repository.pms;

import com.axiaobug.pojo.pms.PmsProductCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface PmsProductCategoryRepository
        extends JpaRepository<PmsProductCategory,Integer>,
        JpaSpecificationExecutor<PmsProductCategory> {
}
