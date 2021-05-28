package com.axiaobug.db.sms;

import com.axiaobug.pojo.sms.SmsCoupon;
import com.axiaobug.pojo.sms.SmsCouponHistory;
import com.axiaobug.pojo.sms.SmsCouponProductCategoryRelation;
import com.axiaobug.pojo.sms.SmsCouponProductRelation;
import com.axiaobug.repository.sms.SmsCouponHistoryRepository;
import com.axiaobug.repository.sms.SmsCouponProductCategoryRelationRepository;
import com.axiaobug.repository.sms.SmsCouponProductRelationRepository;
import com.axiaobug.repository.sms.SmsCouponRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@SpringBootTest
@Transactional
public class CouponTest {

    @Autowired
    private SmsCouponRepository smsCouponRepository;

    @Autowired
    private SmsCouponHistoryRepository smsCouponHistoryRepository;

    @Autowired
    private SmsCouponProductRelationRepository smsCouponProductRelationRepository;

    @Autowired
    private SmsCouponProductCategoryRelationRepository smsCouponProductCategoryRelationRepository;

    @Test
    public void couponTest(){
        long expected = smsCouponRepository.count();
        SmsCoupon coupon = new SmsCoupon();
        coupon.setCode("ajaja");
        coupon.setName("老板朋友");
        smsCouponRepository.save(coupon);
//        Assert.assertEquals(expected+1,smsCouponRepository.count());
    }

    @Test
    public void couponHistoryTest(){
        long expected = smsCouponHistoryRepository.count();
        SmsCouponHistory couponHistory = new SmsCouponHistory();
        couponHistory.setCouponCode("sdfad");
        smsCouponHistoryRepository.save(couponHistory);
//        Assert.assertEquals(expected+1,smsCouponHistoryRepository.count());
    }

    @Test
    public void couponProductRelationTest(){
        long expected = smsCouponProductRelationRepository.count();
        SmsCouponProductRelation couponProductRelation = new SmsCouponProductRelation();
        couponProductRelation.setProductName("华硕3090Ti");
        smsCouponProductRelationRepository.save(couponProductRelation);
//        Assert.assertEquals(expected+1,smsCouponProductRelationRepository.count());
    }

    @Test
    public void couponProductCategoryRelationTest(){
        long expected = smsCouponProductCategoryRelationRepository.count();
        SmsCouponProductCategoryRelation couponProductCategoryRelation = new SmsCouponProductCategoryRelation();
        couponProductCategoryRelation.setProductCategoryName("电脑");
        smsCouponProductCategoryRelationRepository.save(couponProductCategoryRelation);
//        Assert.assertEquals(expected+1,smsCouponProductCategoryRelationRepository.count());
    }

}
