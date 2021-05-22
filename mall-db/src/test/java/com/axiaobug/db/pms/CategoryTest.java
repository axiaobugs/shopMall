package com.axiaobug.db.pms;

import com.axiaobug.pojo.pms.PmsProductCategory;
import com.axiaobug.repository.pms.PmsProductCategoryRepository;
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
public class CategoryTest {

    @Autowired
    private PmsProductCategoryRepository pmsProductCategoryRepository;

    @Test
    public void categoryTest(){
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setName("保健品");
        productCategory.setProductCount(100);
        pmsProductCategoryRepository.save(productCategory);
        long count = pmsProductCategoryRepository.count();
        Assert.assertEquals(37,count);
    }


}
