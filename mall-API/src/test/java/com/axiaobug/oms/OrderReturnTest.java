package com.axiaobug.oms;

import cn.hutool.core.date.DateUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.dto.OmsReturnApplyQueryParam;
import com.axiaobug.repository.oms.OmsOrderRepository;
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


        Assertions.assertEquals(200,result.getResponse().getStatus());

    }
}
