package com.axiaobug.oms;

import cn.hutool.core.date.DateUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.dto.OmsReturnApplyQueryParam;
import com.axiaobug.dto.OmsUpdateStatusParam;
import com.axiaobug.pojo.oms.OmsOrderReturnApply;
import com.axiaobug.repository.oms.OmsOrderRepository;
import com.axiaobug.repository.oms.OmsOrderReturnApplyRepository;
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
    private MockMvc mockMvc;


    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("分页按条件查询退货申请")
    @Test
    public void listTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
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
        Assertions.assertEquals(200,result.getResponse().getStatus());
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
        Assertions.assertEquals(200,result.getResponse().getStatus());
    }

    @DisplayName("获取退货申请详情")
    @Test
    public void getItemTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/returnApply/4")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
        System.out.println(result.getResponse().getContentAsString());
    }

    @DisplayName("修改退货申请状态")
    @Test
    public void updateStatusTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        OmsUpdateStatusParam updateStatusParam = new OmsUpdateStatusParam();
        updateStatusParam.setId(4);
        updateStatusParam.setStatus(3);
        updateStatusParam.setHandleMan("Yanxiao");


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/returnApply/update/status")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateStatusParam))).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
        Assertions.assertEquals(3,applyRepository.findById(4).get().getStatus());
    }
}
