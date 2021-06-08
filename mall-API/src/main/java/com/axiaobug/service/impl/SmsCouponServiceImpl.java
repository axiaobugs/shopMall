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
    public Boolean delete(Integer id) throws Exception {
        if (couponRepository.findById(id).isPresent()){
            try {
                couponRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        throw new Exception("删除优惠卷发生异常,操作回滚.请检查id是否正确");
    }

    @Override
    public Boolean update(Integer id, SmsCoupon couponParam) throws Exception {
        if (couponRepository.findById(id).isPresent()){
            couponParam.setId(id);
            try {
                couponRepository.save(couponParam);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("更新优惠卷发生异常,操作回滚.请检查id是否正确");
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
