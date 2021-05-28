package com.axiaobug.db.oms;

import com.axiaobug.pojo.oms.OmsOrder;
import com.axiaobug.pojo.oms.OmsOrderItem;
import com.axiaobug.pojo.oms.OmsOrderOperateHistory;
import com.axiaobug.repository.oms.OmsOrderItemsRepository;
import com.axiaobug.repository.oms.OmsOrderOperateHistoryRepository;
import com.axiaobug.repository.oms.OmsOrderRepository;
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
public class OmsOrderTest {

    @Autowired
    private OmsOrderRepository omsOrderRepository;

    @Autowired
    private OmsOrderItemsRepository omsOrderItemsRepository;

    @Autowired
    private OmsOrderOperateHistoryRepository omsOrderOperateHistoryRepository;


    @Test
    public void orderTest(){
        OmsOrder order = new OmsOrder();
        order.setMemberUsername("axiaobug");
        order.setTotalAmount(BigDecimal.valueOf(5999));
        omsOrderRepository.save(order);
//        Assert.assertEquals(20,omsOrderRepository.count());
        List<OmsOrder> orders = omsOrderRepository.findAll();

        for (OmsOrder omsOrder:orders) {
            if (omsOrder.getId() > 30){
                System.out.println(omsOrderRepository.findById(omsOrder.getId()).toString());
            }
        }

    }

    @Test
    public void orderItemsTest(){
        OmsOrderItem orderItem = new OmsOrderItem();
        orderItem.setOrderId(12);
        omsOrderItemsRepository.save(orderItem);
//        Assert.assertEquals(33,omsOrderItemsRepository.count());
        List<OmsOrderItem> orderItems = omsOrderItemsRepository.findAll();
        orderItems.forEach(System.out::println);
    }

    @Test
    public void orderOperateHistoryTest(){
        OmsOrderOperateHistory orderOperateHistory = new OmsOrderOperateHistory();
        orderOperateHistory.setOrderId(12);
        omsOrderOperateHistoryRepository.save(orderOperateHistory);
        try {
//            Assert.assertEquals(23,omsOrderOperateHistoryRepository.count());
            System.out.println("成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败");
        }
    }


}
