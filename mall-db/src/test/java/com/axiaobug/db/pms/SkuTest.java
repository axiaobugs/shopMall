package com.axiaobug.db.pms;

import com.axiaobug.pojo.pms.PmsSkuStock;
import com.axiaobug.repository.pms.PmsSkuStockRepository;
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
public class SkuTest {

    @Autowired
    private PmsSkuStockRepository pmsSkuStockRepository;

    @Test
    public void skuTest(){
        PmsSkuStock skuStock = new PmsSkuStock();
        skuStock.setProductId(26);
        skuStock.setSkuCode("q223425345234");
        pmsSkuStockRepository.save(skuStock);
        List<PmsSkuStock> skuStocks = pmsSkuStockRepository.findAll();
        skuStocks.forEach(System.out::println);
//        Assert.assertEquals(33,pmsSkuStockRepository.count());
    }



}
