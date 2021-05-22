package com.axiaobug.db.pms;

import com.axiaobug.pojo.pms.PmsProductAttribute;
import com.axiaobug.pojo.pms.PmsProductAttributeCategory;
import com.axiaobug.repository.pms.PmsProductAttributeCategoryRepository;
import com.axiaobug.repository.pms.PmsProductAttributeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AttributeTest {

    @Autowired
    private PmsProductAttributeCategoryRepository pmsProductAttributeCategoryRepository;

    @Autowired
    private PmsProductAttributeRepository pmsProductAttributeRepository;

    @Test
    public void attributeCategoryTest(){
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName("电脑");
        productAttributeCategory.setParamCount(5);
        pmsProductAttributeCategoryRepository.save(productAttributeCategory);
        List<PmsProductAttributeCategory> categoryRepositoryAll = pmsProductAttributeCategoryRepository.findAll();
        Assert.assertEquals(9,categoryRepositoryAll.size());
    }


    @Test
    public void attributeTest(){
        PmsProductAttribute productAttribute = new PmsProductAttribute();
        productAttribute.setName("颜色");
        productAttribute.setProductAttributeCategoryId(2);
        pmsProductAttributeRepository.save(productAttribute);
        long count = pmsProductAttributeRepository.count();
        Assert.assertEquals(32,count);

    }


}
