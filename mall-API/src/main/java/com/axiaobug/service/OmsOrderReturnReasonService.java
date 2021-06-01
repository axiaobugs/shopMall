package com.axiaobug.service;

import com.axiaobug.pojo.oms.OmsOrderReturnReason;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface OmsOrderReturnReasonService {
    /**
     * 添加退货原因
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean create(OmsOrderReturnReason returnReason);

    /**
     * 修改退货原因
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean update(OmsOrderReturnReason returnReason) throws Exception;

    /**
     * 批量删除退货原因
     */
    @Transactional(rollbackFor = Exception.class)
    Integer delete(List<Integer> ids) throws Exception;

    /**
     * 分页获取退货原因
     */
    List<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum);

    /**
     * 批量修改退货原因状态
     */
    Integer updateStatus(List<Integer> ids, Integer status) throws Exception;

    /**
     * 获取单个退货原因详情信息
     */
    OmsOrderReturnReason getItem(Integer id);
}
