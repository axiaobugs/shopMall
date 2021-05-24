package com.axiaobug.db.oms;

import com.axiaobug.pojo.oms.OmsCompanyAddress;
import com.axiaobug.pojo.oms.OmsOrderReturnApply;
import com.axiaobug.repository.oms.OmsCompanyAddressRepository;
import com.axiaobug.repository.oms.OmsOrderReturnApplyRepository;
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
public class OrderReturnTest {

    @Autowired
    private OmsOrderReturnApplyRepository omsOrderReturnApplyRepository;

    @Autowired
    private OmsCompanyAddressRepository omsCompanyAddressRepository;

    @Test
    public void orderReturnTest(){
        OmsOrderReturnApply orderReturnApply = new OmsOrderReturnApply();
        orderReturnApply.setOrderId(12);
        orderReturnApply.setMemberUsername("axiaobug");
        omsOrderReturnApplyRepository.save(orderReturnApply);
        try {
            Assert.assertEquals(22,omsOrderReturnApplyRepository.count());
            System.out.println("成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败");
        }
    }

    @Test
    public void returnCompanyAddressTest(){
        OmsCompanyAddress companyAddress = new OmsCompanyAddress();
        companyAddress.setAddressName("武汉发货点");
        omsCompanyAddressRepository.save(companyAddress);
        try {
            Assert.assertEquals(4,omsCompanyAddressRepository.count());
            System.out.println("成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败");
        }
    }


}
