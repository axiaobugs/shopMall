package com.axiaobug.repository.cms;

import com.axiaobug.pojo.cms.CmsPrefrenceArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface CmsPrefrenceAreaRepository extends JpaRepository<CmsPrefrenceArea,Integer>, JpaSpecificationExecutor<CmsPrefrenceArea> {
}
