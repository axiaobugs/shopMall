package com.axiaobug.db.pms;

import com.axiaobug.pojo.pms.PmsProductAttribute;
import com.axiaobug.pojo.pms.PmsProductAttributeCategory;
import com.axiaobug.pojo.pms.PmsProductAttributeValue;
import com.axiaobug.repository.pms.PmsProductAttributeCategoryRepository;
import com.axiaobug.repository.pms.PmsProductAttributeRepository;
import com.axiaobug.repository.pms.PmsProductAttributeValueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@SpringBootTest
@Transactional
public class AttributeTest {

    @Autowired
    private PmsProductAttributeCategoryRepository pmsProductAttributeCategoryRepository;

    @Autowired
    private PmsProductAttributeRepository pmsProductAttributeRepository;

    @Autowired
    private PmsProductAttributeValueRepository pmsProductAttributeValueRepository;

    @Test
    public void attributeCategoryTest(){
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName("电脑");
        productAttributeCategory.setParamCount(5);
        pmsProductAttributeCategoryRepository.save(productAttributeCategory);
        List<PmsProductAttributeCategory> categoryRepositoryAll = pmsProductAttributeCategoryRepository.findAll();
//        Assert.assertEquals(9,categoryRepositoryAll.size());
    }


    @Test
    public void attributeTest(){
        PmsProductAttribute productAttribute = new PmsProductAttribute();
        productAttribute.setName("颜色");
        productAttribute.setProductAttributeCategoryId(2);
        pmsProductAttributeRepository.save(productAttribute);
        long count = pmsProductAttributeRepository.count();
//        Assert.assertEquals(32,count);
    }

    @Test
    public void attributeValueTest(){
        PmsProductAttributeValue productAttributeValue = new PmsProductAttributeValue();
        pmsProductAttributeValueRepository.save(productAttributeValue);
//        Assert.assertEquals(71,pmsProductAttributeValueRepository.count());
    }

}
