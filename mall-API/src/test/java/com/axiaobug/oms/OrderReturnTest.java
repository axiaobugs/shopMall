package com.axiaobug.oms;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.dto.OmsReturnApplyQueryParam;
import com.axiaobug.dto.OmsUpdateStatusParam;
import com.axiaobug.pojo.oms.OmsOrderReturnApply;
import com.axiaobug.pojo.oms.OmsOrderReturnReason;
import com.axiaobug.repository.oms.OmsOrderRepository;
import com.axiaobug.repository.oms.OmsOrderReturnApplyRepository;
import com.axiaobug.repository.oms.OmsOrderReturnReasonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@SpringBootTest(classes = MallApiApplication.class)
@Transactional
public class OrderReturnTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private OmsOrderRepository omsOrderRepository;

    @Autowired
    private OmsOrderReturnApplyRepository applyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OmsOrderReturnReasonRepository reasonRepository;

    private MockMvc mockMvc;


    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("分页按条件查询退货申请")
    @Test
    public void listTest() throws Exception {
        OmsReturnApplyQueryParam applyQueryParam = new OmsReturnApplyQueryParam();
        applyQueryParam.setPageNum(0);
        applyQueryParam.setPageSize(2);
        applyQueryParam.setCreateTime(DateUtil.parse("2018-10-17"));


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/returnApply/list")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(applyQueryParam))).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        Assertions.assertEquals("200",JSONUtil.parseObj(result.getResponse().getContentAsString()).getStr("code"));
    }

    @DisplayName("批量删除退货申请")
    @Test
    public void deleteTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/returnApply/delete")
                .param("ids","24,25,26")
                .param("note","customer need return")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        if (applyRepository.findById(25).isPresent()) {
           Assertions.assertEquals(3,applyRepository.findById(25).get().getStatus());
        }
        Assertions.assertEquals("200",JSONUtil.parseObj(result.getResponse().getContentAsString()).getStr("code"));
    }

    @DisplayName("获取退货申请详情")
    @Test
    public void getItemTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/returnApply/4")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals("200",JSONUtil.parseObj(result.getResponse().getContentAsString()).getStr("code"));
        System.out.println(result.getResponse().getContentAsString());
    }

    @DisplayName("修改退货申请状态")
    @Test
    public void updateStatusTest() throws Exception {
        OmsUpdateStatusParam updateStatusParam = new OmsUpdateStatusParam();
        updateStatusParam.setId(4);
        updateStatusParam.setStatus(3);
        updateStatusParam.setHandleMan("Yanxiao");


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/returnApply/update/status")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateStatusParam))).andReturn();
        Assertions.assertEquals("200",JSONUtil.parseObj(result.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(3,applyRepository.findById(4).get().getStatus());
    }

    @DisplayName("添加退货原因")
    @Test
    public void returnReasonCreateTest() throws Exception {
        OmsOrderReturnReason returnReason = new OmsOrderReturnReason();
        returnReason.setName("No fashion,too old");
        returnReason.setStatus(1);
        returnReason.setSort(0);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/returnReason/create")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(returnReason))).andReturn();
        Assertions.assertEquals("200",JSONUtil.parseObj(result.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(10,reasonRepository.count());
    }


    @DisplayName("修改退货原因")
    @Test
    public void updateReasonTest() throws Exception {
        OmsOrderReturnReason reason = reasonRepository.findById(5).get();
        reason.setStatus(1);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .patch("/returnReason/update")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reason))).andReturn();
        Assertions.assertEquals("200",JSONUtil.parseObj(result.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(1,reasonRepository.findById(5).get().getStatus());
        Assertions.assertEquals(9,reasonRepository.count());
    }

    @DisplayName("批量删除退货原因")
    @Test
    public void deleteReasonTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/returnReason/delete")
                .param("ids","12,13,14")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(6,reasonRepository.count());
    }

    @DisplayName("分页查询全部退货原因")
    @Test
    public void getReasonPageableTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/returnReason/list")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals("200",JSONUtil.parseObj(result.getResponse().getContentAsString()).getStr("code"));
        System.out.println(result.getResponse().getContentAsString());
    }

    @DisplayName("获取单个退货原因详情信息")
    @Test
    public void getReasonDetailTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/returnReason/13")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals("200",JSONUtil.parseObj(result.getResponse().getContentAsString()).getStr("code"));

        MvcResult result2 = mockMvc.perform(MockMvcRequestBuilders
                .get("/returnReason/17")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals("500",JSONUtil.parseObj(result2.getResponse().getContentAsString()).getStr("code"));
    }

    @DisplayName("批量修改退货原因启用状态")
    @Test
    public void updateStatusBulkTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/returnReason/update/status")
                .param("ids","12,13,14")
                .param("status","0")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200",JSONUtil.parseObj(result.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(0,reasonRepository.findById(12).get().getStatus());
    }

}
