package com.axiaobug.repository.cms;

import com.axiaobug.pojo.cms.CmsSubjectProductRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface CmsSubjectProductRelationRepository
        extends JpaRepository<CmsSubjectProductRelation,Integer>,
        JpaSpecificationExecutor<CmsSubjectProductRelation> {
}
