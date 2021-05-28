package com.axiaobug.db.pms;

import com.axiaobug.pojo.pms.PmsProductAttribute;
import com.axiaobug.pojo.pms.PmsProductCategory;
import com.axiaobug.pojo.pms.PmsProductCategoryAttributeRelation;
import com.axiaobug.repository.pms.PmsProductAttributeRepository;
import com.axiaobug.repository.pms.PmsProductCategoryAttributeRelationRepository;
import com.axiaobug.repository.pms.PmsProductCategoryRepository;
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
public class CategoryTest {

    @Autowired
    private PmsProductCategoryRepository pmsProductCategoryRepository;

    @Autowired
    private PmsProductAttributeRepository pmsProductAttributeRepository;

    @Autowired
    private PmsProductCategoryAttributeRelationRepository pmsProductCategoryAttributeRelationRepository;

    @Test
    public void categoryTest(){
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setName("保健品");
        productCategory.setProductCount(100);
        pmsProductCategoryRepository.save(productCategory);
        long count = pmsProductCategoryRepository.count();
//        Assert.assertEquals(37,count);
    }

    @Test
    public void categoryAttributeTest(){
        PmsProductAttribute productAttribute = new PmsProductAttribute();
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setId(99999);
        productAttribute.setId(88888);
        pmsProductCategoryRepository.save(productCategory);
        pmsProductAttributeRepository.save(productAttribute);
        PmsProductCategoryAttributeRelation productCategoryAttributeRelation = new PmsProductCategoryAttributeRelation();
        productCategoryAttributeRelation.setProductCategoryId(productCategory.getId());
        productCategoryAttributeRelation.setProductAttributeId(productAttribute.getId());
        pmsProductCategoryAttributeRelationRepository.save(productCategoryAttributeRelation);
//        Assert.assertEquals(6,pmsProductCategoryAttributeRelationRepository.count());


    }


}
