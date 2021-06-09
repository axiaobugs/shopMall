package com.axiaobug.service.impl;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.dto.SmsCouponParam;
import com.axiaobug.pojo.sms.SmsCoupon;
import com.axiaobug.repository.sms.SmsCouponRepository;
import com.axiaobug.service.SmsCouponService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        System.out.println("name: "+name);
        System.out.println("type: "+type);
        if (name==null && type==null){
            System.out.println("jin");
            return couponRepository.findAll(pageable).getContent();
        }else {
            SmsCoupon coupon = new SmsCoupon();
            if (name!=null){
                coupon.setName(name);
            }else if (type!=null){
                coupon.setType(type);
            }
            Example<SmsCoupon> example = Example.of(coupon);
            return couponRepository.findAll(example,pageable).getContent();
        }
    }

    @Override
    public SmsCoupon getItem(Integer id) {
        if (couponRepository.findById(id).isPresent()){
            return couponRepository.findById(id).get();
        }
        return null;
    }
}
