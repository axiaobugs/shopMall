package com.axiaobug.db.pms;

import com.axiaobug.pojo.pms.PmsProductVertifyRecord;
import com.axiaobug.repository.pms.PmsProductVertifyRecordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@SpringBootTest
@Transactional
public class VerifyRecordTest {

    @Autowired
    private PmsProductVertifyRecordRepository pmsProductVertifyRecordRepository;

    @Test
    public void recordTest(){
        PmsProductVertifyRecord productVertifyRecord = new PmsProductVertifyRecord();
        productVertifyRecord.setCreateTime(new Date());
        productVertifyRecord.setVerifyMan("admin");
        pmsProductVertifyRecordRepository.save(productVertifyRecord);
//        Assert.assertEquals(3,pmsProductVertifyRecordRepository.count());
        List<PmsProductVertifyRecord> records = pmsProductVertifyRecordRepository.findAll();
        records.forEach(System.out::println);
    }

}
