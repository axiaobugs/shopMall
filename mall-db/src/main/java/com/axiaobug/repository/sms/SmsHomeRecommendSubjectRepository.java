package com.axiaobug.repository.sms;

import com.axiaobug.pojo.sms.SmsHomeRecommendSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface SmsHomeRecommendSubjectRepository
        extends JpaRepository<SmsHomeRecommendSubject,Integer>,
        JpaSpecificationExecutor<SmsHomeRecommendSubject> {
}
