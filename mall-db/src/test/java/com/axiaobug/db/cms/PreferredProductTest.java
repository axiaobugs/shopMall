package com.axiaobug.db.cms;

import com.axiaobug.pojo.cms.CmsPrefrenceArea;
import com.axiaobug.pojo.cms.CmsPrefrenceAreaProductRelation;
import com.axiaobug.repository.cms.CmsPrefrenceAreaProductRelationRepository;
import com.axiaobug.repository.cms.CmsPrefrenceAreaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@SpringBootTest
@Transactional
public class PreferredProductTest {

    @Autowired
    private CmsPrefrenceAreaProductRelationRepository cmsPrefrenceAreaProductRelationRepository;

    @Autowired
    private CmsPrefrenceAreaRepository cmsPrefrenceAreaRepository;


    @Test
    public void preferredAreaTest(){
        long expected = cmsPrefrenceAreaRepository.count();
        CmsPrefrenceArea prefrenceArea = new CmsPrefrenceArea();
        prefrenceArea.setName("lingting");
        cmsPrefrenceAreaRepository.save(prefrenceArea);
//        Assert.assertEquals(expected+1,cmsPrefrenceAreaRepository.count());
    }

    @Test
    public void preferredProductTest(){
        long expected = cmsPrefrenceAreaProductRelationRepository.count();
        CmsPrefrenceAreaProductRelation productRelation = new CmsPrefrenceAreaProductRelation();
        productRelation.setProductId(12);
        cmsPrefrenceAreaProductRelationRepository.save(productRelation);
//        Assert.assertEquals(expected+1,cmsPrefrenceAreaProductRelationRepository.count());
    }


}
