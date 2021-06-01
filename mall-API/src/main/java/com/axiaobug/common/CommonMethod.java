package com.axiaobug.common;

import cn.hutool.core.date.DateUtil;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Component
public class CommonMethod {
    public void timeRangeMatch(List<Predicate> predicates,
                                          Date createTime, Root root,
                                          CriteriaQuery criteriaQuery,
                                          CriteriaBuilder criteriaBuilder,
                                            String filed){
        Date beginOfDay = DateUtil.beginOfDay(createTime);
        Date endOfDay = DateUtil.endOfDay(createTime);
        predicates.add(criteriaBuilder.between(root.get(filed).as(String.class), beginOfDay.toString(), endOfDay.toString()));
    }
}
