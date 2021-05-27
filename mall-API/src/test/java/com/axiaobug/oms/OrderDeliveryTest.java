package com.axiaobug.oms;



import com.axiaobug.MallApiApplication;
import com.axiaobug.common.CommonResult;
import com.axiaobug.controller.OmsOrderController;
import com.axiaobug.dto.OmsOrderDeliveryParam;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@SpringBootTest(classes = MallApiApplication.class)
@RunWith(SpringRunner.class)
@Transactional
public class OrderDeliveryTest {

    @Autowired
    protected OmsOrderController omsOrderController;


    @Test
    public void deliveryTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        OmsOrderDeliveryParam deliveryParam1 = new OmsOrderDeliveryParam();
        deliveryParam1.setId(28);
        deliveryParam1.setDeliveryCompany("Amazon");
        deliveryParam1.setDeliverySerialNum("14221255210");

        OmsOrderDeliveryParam deliveryParam2 = new OmsOrderDeliveryParam();
        deliveryParam2.setId(29);
        deliveryParam2.setDeliveryCompany("Post");
        deliveryParam2.setDeliverySerialNum("14221255211");

        List<OmsOrderDeliveryParam> requestBody = new ArrayList<>();
        requestBody.add(deliveryParam1);
        requestBody.add(deliveryParam2);

//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order/update/delivery")
//                .accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestBody))
//                .contentType(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//        System.out.println(response.getStatus());
        CommonResult<?> commonResult = omsOrderController.delivery(requestBody);
        System.out.println(commonResult.getData());

    }
}
