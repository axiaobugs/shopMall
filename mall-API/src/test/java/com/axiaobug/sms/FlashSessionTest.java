package com.axiaobug.sms;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.pojo.sms.SmsFlashPromotion;
import com.axiaobug.pojo.sms.SmsFlashPromotionSession;
import com.axiaobug.repository.sms.SmsFlashPromotionRepository;
import com.axiaobug.repository.sms.SmsFlashPromotionSessionRepository;
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
import java.util.Date;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@SpringBootTest(classes = MallApiApplication.class)
@Transactional
public class FlashSessionTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SmsFlashPromotionSessionRepository sessionRepository;


    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("添加活动场次")
    @Test
    public void createPromotionSessionTest() throws Exception {
        SmsFlashPromotionSession promotion = new SmsFlashPromotionSession();
        promotion.setStatus(1);
        promotion.setCreateTime(new Date());
        promotion.setName("墨尔本吴彦祖");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/flashSession/create")
                .content(objectMapper.writeValueAsString(promotion))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(8,sessionRepository.count());
    }

    @DisplayName("修改活动场次")
    @Test
    public void UpdatePromotionSessionTest() throws Exception {
        SmsFlashPromotionSession promotion = new SmsFlashPromotionSession();
        promotion.setStatus(1);
        promotion.setCreateTime(new Date());
        promotion.setName("墨尔本吴彦祖");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/flashSession/update/7")
                .content(objectMapper.writeValueAsString(promotion))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals("墨尔本吴彦祖",sessionRepository.findById(7).get().getName());
    }

    @DisplayName("修改活动场次启用状态")
    @Test
    public void UpdatePromotionSessionStatusTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/flashSession/update/status/7")
                .param("status","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(1,sessionRepository.findById(7).get().getStatus());
    }

    @DisplayName("删除活动场次")
    @Test
    public void deletePromotionSessionTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/flashSession/delete/7")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(6,sessionRepository.count());
    }

    @DisplayName("获取场次详情")
    @Test
    public void getPromotionSessionTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/flashSession/7")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        SmsFlashPromotionSession session = objectMapper.readValue(JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data"), SmsFlashPromotionSession.class);
        Assertions.assertEquals(0,session.getStatus());
    }

    @DisplayName("获取全部场次")
    @Test
    public void getAllPromotionSessionTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/flashSession/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        ArrayList list = objectMapper.readValue(JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data"), ArrayList.class);
        Assertions.assertEquals(7,list.size());
    }


}
