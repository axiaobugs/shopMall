package com.axiaobug.db.pms;

import com.axiaobug.pojo.pms.*;
import com.axiaobug.repository.pms.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ProductsTest {

    @Autowired
    private PmsProductRepository pmsProductRepository;

    @Autowired
    private PmsBrandRepository pmsBrandRepository;

    @Autowired
    private PmsFeightTemplateRepository pmsFeightTemplateRepository;

    @Autowired
    private PmsProductAttributeCategoryRepository pmsProductAttributeCategoryRepository;

    @Autowired
    private PmsProductLadderRepository pmsProductLadderRepository;

    @Test
    public void productTest(){
        PmsProduct product = new PmsProduct();
        product.setName("华为 Mate 40 5G");
        product.setPrice(BigDecimal.valueOf(2001));
        pmsProductRepository.save(product);
        List<PmsProduct> afterList = pmsProductRepository.findAll();
        Assert.assertEquals(30,afterList.size());
    }

    @Test
    public void brandTest(){
        PmsBrand brand = new PmsBrand();
        brand.setName("SONY");
        pmsBrandRepository.save(brand);
        List<PmsBrand> brandList = pmsBrandRepository.findAll();
        Assert.assertEquals(12,brandList.size());
    }


    @Test
    public void feightTemplateTest(){
        PmsFeightTemplate feightTemplate = new PmsFeightTemplate();
        feightTemplate.setName("免邮费");
        feightTemplate.setFirstWeight(BigDecimal.valueOf(0.5));
        pmsFeightTemplateRepository.save(feightTemplate);
        List<PmsFeightTemplate> feightTemplateList = pmsFeightTemplateRepository.findAll();
        Assert.assertEquals(1,feightTemplateList.size());
    }


    @Test
    public void attributeCategoryTest(){
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName("haha");
        pmsProductAttributeCategoryRepository.save(productAttributeCategory);
        List<PmsProductAttributeCategory> attributeCategories = pmsProductAttributeCategoryRepository.findAll();
        Assert.assertEquals(9,attributeCategories.size());
    }

    @Test
    public void productLadderTest(){
        PmsProductLadder productLadder = new PmsProductLadder();
        productLadder.setCount(10);
        productLadder.setDiscount(BigDecimal.valueOf(0.95));
        pmsProductLadderRepository.save(productLadder);
        List<PmsProductLadder> ladders = pmsProductLadderRepository.findAll();
        Assert.assertEquals(27,ladders.size());
    }

}
