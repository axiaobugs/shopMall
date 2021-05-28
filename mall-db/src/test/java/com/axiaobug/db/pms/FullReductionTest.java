package com.axiaobug.db.pms;

import com.axiaobug.pojo.pms.PmsProductFullReduction;
import com.axiaobug.repository.pms.PmsProductFullReductionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@SpringBootTest
@Transactional
public class FullReductionTest {

    @Autowired
    private PmsProductFullReductionRepository pmsProductFullReductionRepository;

    @Test
    public void fullReductionTest(){
//        Assert.assertEquals(26,pmsProductFullReductionRepository.count());
        PmsProductFullReduction productFullReduction = new PmsProductFullReduction();
        productFullReduction.setFullPrice(BigDecimal.valueOf(500));
        productFullReduction.setReducePrice(BigDecimal.valueOf(50));
        pmsProductFullReductionRepository.save(productFullReduction);
//        Assert.assertEquals(27,pmsProductFullReductionRepository.count());
        List<PmsProductFullReduction> reductions = pmsProductFullReductionRepository.findAll();
        reductions.forEach(System.out::println);
    }


}
