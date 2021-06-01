package com.axiaobug.service;

import com.axiaobug.dto.OmsOrderReturnApplyResult;
import com.axiaobug.dto.OmsReturnApplyQueryParam;
import com.axiaobug.dto.OmsUpdateStatusParam;
import com.axiaobug.pojo.oms.OmsOrderReturnApply;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface OmsOrderReturnApplyService {
    /**
     * 分页查询申请
     */
    Specification<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam);

    /**
     * 批量删除申请
     */
    @Transactional(rollbackFor = Exception.class)
    int delete(List<Integer> ids,String note);

    /**
     * 修改申请状态
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean updateStatus(OmsUpdateStatusParam statusParam) throws Exception;

    /**
     * 获取指定申请详情
     */
    OmsOrderReturnApplyResult getItem(Integer id) throws Exception;
}
