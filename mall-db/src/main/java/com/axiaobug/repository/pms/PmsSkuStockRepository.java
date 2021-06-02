package com.axiaobug.repository.pms;

import com.axiaobug.pojo.pms.PmsSkuStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface PmsSkuStockRepository extends JpaRepository<PmsSkuStock, Integer>, JpaSpecificationExecutor<PmsSkuStock> {
    List<PmsSkuStock> findByProductIdAndSpDataContaining(Integer productId,String name);
    List<PmsSkuStock> findByProductId(Integer pid);
    PmsSkuStock findByIdAndSkuCode(Integer id,String code);
}
