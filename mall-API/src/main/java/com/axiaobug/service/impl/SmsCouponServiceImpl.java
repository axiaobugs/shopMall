package com.axiaobug.service.impl;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.dto.SmsCouponParam;
import com.axiaobug.pojo.sms.SmsCoupon;
import com.axiaobug.repository.sms.SmsCouponProductCategoryRelationRepository;
import com.axiaobug.repository.sms.SmsCouponProductRelationRepository;
import com.axiaobug.repository.sms.SmsCouponRepository;
import com.axiaobug.service.SmsCouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class SmsCouponServiceImpl implements SmsCouponService {

    @Resource
    CommonMethod commonMethod;

    @Resource
    private SmsCouponRepository couponRepository;

    @Resource
    private SmsCouponProductRelationRepository productRelationRepository;

    @Resource
    private SmsCouponProductCategoryRelationRepository categoryRelationRepository;

    @Override
    public Boolean create(SmsCoupon couponParam) throws Exception {
        if (!commonMethod.isEmptyObject(couponParam)){
            try {
                couponRepository.save(couponParam);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("创建优惠卷发生异常,操作回滚.请检查参数");
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public int update(Integer id, SmsCouponParam couponParam) {
        return 0;
    }

    @Override
    public List<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public SmsCouponParam getItem(Integer id) {
        return null;
    }
}
