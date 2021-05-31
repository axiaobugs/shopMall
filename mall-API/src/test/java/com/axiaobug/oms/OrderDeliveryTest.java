package com.axiaobug.oms;

import com.axiaobug.MallApiApplication;
import com.axiaobug.dto.OmsOrderDeliveryParam;
import com.axiaobug.dto.OmsOrderQueryParam;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@SpringBootTest(classes = MallApiApplication.class)
@Transactional
public class OrderDeliveryTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }




    @DisplayName("批量发货")
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

        System.out.println(objectMapper.writeValueAsString(requestBody));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/order/update/delivery")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andReturn();
        Assertions.assertEquals(200,mvcResult.getResponse().getStatus());

    }

    @DisplayName("按条件查询所有订单(分页)")
    @Test
    public void listTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        OmsOrderQueryParam queryParam = new OmsOrderQueryParam();
        queryParam.setPageNum(0);
        queryParam.setPageSize(2);
        queryParam.setStatus(2);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/order/list")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(queryParam))).andReturn();


        Assertions.assertEquals(200,result.getResponse().getStatus());
    }
}
