package com.axiaobug.oms;

import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.dto.OmsMoneyInfoParam;
import com.axiaobug.dto.OmsReceiverInfoParam;
import com.axiaobug.pojo.oms.OmsOrder;
import com.axiaobug.pojo.oms.OmsOrderReturnReason;
import com.axiaobug.pojo.oms.OmsOrderSetting;
import com.axiaobug.repository.oms.OmsOrderRepository;
import com.axiaobug.repository.oms.OmsOrderSettingRepository;
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
import java.math.BigDecimal;
import java.util.Optional;


/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@SpringBootTest(classes = MallApiApplication.class)
@Transactional
public class OrderTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private OmsOrderRepository omsOrderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OmsOrderSettingRepository settingRepository;


    private MockMvc mockMvc;


    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("批量关闭订单")
    @Test
    public void orderCloseTest() throws Exception {
        String note = "Customer close order";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/order/update/close")
                .param("ids","12,13,14")
                .param("note",note)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
    }

    @DisplayName("批量删除订单")
    @Test
    public void orderDeleteTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/order/delete")
                .param("ids", "12,13,14")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        if (omsOrderRepository.findById(12).isPresent()){
            OmsOrder order = omsOrderRepository.findById(12).get();
            try {
                Assertions.assertEquals(1,order.getDeleteStatus());
                System.out.println("测试通过");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
    }

    @DisplayName("获取指定订单详情")
    @Test
    public void orderDetailTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/order/12")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
    }

    @DisplayName("修改收货人信息")
    @Test
    public void editReceiverTest() throws Exception {
        OmsReceiverInfoParam receiverInfoParam = new OmsReceiverInfoParam();
        receiverInfoParam.setOrderId(12);
        receiverInfoParam.setReceiverName("方言啸");
        receiverInfoParam.setStatus(5);
        String requestBody = objectMapper.writeValueAsString(receiverInfoParam);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/order/update/receiverInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andReturn();
        OmsOrder order = omsOrderRepository.findById(12).get();
        Assertions.assertEquals("方言啸",order.getReceiverName());
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @DisplayName("修改订单费用信息")
    @Test
    public void editFeeTest() throws Exception {
        OmsMoneyInfoParam moneyInfoParam = new OmsMoneyInfoParam();
        moneyInfoParam.setOrderId(14);
        moneyInfoParam.setFreightAmount(BigDecimal.valueOf(808080));
        String requestBody = objectMapper.writeValueAsString(moneyInfoParam);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/order/update/moneyInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andReturn();
        OmsOrder order = omsOrderRepository.findById(14).get();
        Assertions.assertEquals(BigDecimal.valueOf(808080),
                                order.getFreightAmount());
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @DisplayName("修改订单备注")
    @Test
    public void editNoteTest() throws Exception {
        String note = "Customer close order";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/order/update/note")
                .param("id","13")
                .param("note",note)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        OmsOrder order = omsOrderRepository.findById(13).get();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(note,order.getNote());
    }

    @DisplayName("获取指定订单设置")
    @Test
    public void orderSettingTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/orderSetting/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @DisplayName("修改指定订单设置")
    @Test
    public void returnReasonCreateTest() throws Exception {
        OmsOrderSetting setting = settingRepository.findById(1).get();
        setting.setCommentOverTime(90);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .patch("/returnReason/update")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(setting))).andReturn();
        Assertions.assertEquals("200",JSONUtil.parseObj(result.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(90,settingRepository.findById(1).get().getCommentOverTime());
    }



}
