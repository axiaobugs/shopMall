package com.axiaobug.service;

import com.axiaobug.pojo.sms.SmsHomeAdvertise;

import java.util.Date;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface SmsHomeAdvertiseService {
    /**
     * 添加广告
     */
    Boolean create(SmsHomeAdvertise advertise) throws Exception;

    /**
     * 批量删除广告
     */
    Boolean delete(List<Integer> ids) throws Exception;

    /**
     * 修改上、下线状态
     */
    Boolean updateStatus(Integer id, Integer status) throws Exception;

    /**
     * 获取广告详情
     */
    SmsHomeAdvertise getItem(Integer id) throws Exception;

    /**
     * 更新广告
     */
    Boolean update(Integer id, SmsHomeAdvertise advertise) throws Exception;

    /**
     * 分页查询广告
     */
    List<SmsHomeAdvertise> list(String name, Integer type, Date endTime, Integer pageSize, Integer pageNum);
}
