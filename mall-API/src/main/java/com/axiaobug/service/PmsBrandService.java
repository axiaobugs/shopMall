package com.axiaobug.service;

import com.axiaobug.dto.PmsBrandParam;
import com.axiaobug.pojo.pms.PmsBrand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
public interface PmsBrandService {
    /**
     * 获取所有品牌
     */
    List<PmsBrand> listAllBrand();

    /**
     * 创建品牌
     */
    Boolean createBrand(PmsBrandParam pmsBrandParam);

    /**
     * 修改品牌
     */
    @Transactional
    Boolean updateBrand(Integer id, PmsBrandParam pmsBrandParam);

    /**
     * 删除品牌
     */
    Boolean deleteBrand(Integer id);

    /**
     * 批量删除品牌
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean deleteBrand(List<Integer> ids) throws Exception;

    /**
     * 分页查询品牌
     */
    List<PmsBrand> listBrand(String keyword, int pageNum, int pageSize);

    /**
     * 获取品牌详情
     */
    PmsBrand getBrand(Integer id) throws Exception;

    /**
     * 修改显示状态
     */
    Boolean updateShowStatus(List<Integer> ids, Integer showStatus);

    /**
     * 修改厂家制造商状态
     */
    Boolean updateFactoryStatus(List<Integer> ids, Integer factoryStatus);
}
