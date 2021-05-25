package com.axiaobug.db.sms;

import com.axiaobug.pojo.sms.SmsFlashPromotion;
import com.axiaobug.pojo.sms.SmsFlashPromotionProductRelation;
import com.axiaobug.pojo.sms.SmsFlashPromotionSession;
import com.axiaobug.repository.sms.SmsFlashPromotionProductRelationRepository;
import com.axiaobug.repository.sms.SmsFlashPromotionRepository;
import com.axiaobug.repository.sms.SmsFlashPromotionSessionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@RunWith(SpringRunner.class)
@Transactional
public class FlashPromotionTest {

    @Autowired
    private SmsFlashPromotionSessionRepository smsFlashPromotionSessionRepository;

    @Autowired
    private SmsFlashPromotionProductRelationRepository smsFlashPromotionProductRelationRepository;

    @Autowired
    private SmsFlashPromotionRepository smsFlashPromotionRepository;

    @Test
    public void flashPromotionSessionTest(){
        long expect = smsFlashPromotionSessionRepository.count();
        SmsFlashPromotionSession flashPromotionSession = new SmsFlashPromotionSession();
        flashPromotionSession.setName("双十一黄金点");
        smsFlashPromotionSessionRepository.save(flashPromotionSession);
        Assert.assertEquals(expect+1,smsFlashPromotionSessionRepository.count());
    }

    @Test
    public void flashPromotionProductRelationTest(){
        long expected = smsFlashPromotionProductRelationRepository.count();
        SmsFlashPromotionProductRelation flashPromotionProductRelation = new SmsFlashPromotionProductRelation();
        flashPromotionProductRelation.setFlashPromotionSessionId(2);
        flashPromotionProductRelation.setFlashPromotionId(2);
        smsFlashPromotionProductRelationRepository.save(flashPromotionProductRelation);
        Assert.assertEquals(expected+1,smsFlashPromotionProductRelationRepository.count());
    }

    @Test
    public void flashPromotionTest(){
        long expected = smsFlashPromotionRepository.count();
        SmsFlashPromotion flashPromotion = new SmsFlashPromotion();
        flashPromotion.setTitle("电脑WFH全场秒杀");
        smsFlashPromotionRepository.save(flashPromotion);
        Assert.assertEquals(expected+1,smsFlashPromotionRepository.count());
    }

}
