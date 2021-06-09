package com.axiaobug.sms;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.pojo.sms.SmsFlashPromotion;
import com.axiaobug.repository.sms.SmsFlashPromotionRepository;
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
public class FlashPromotionTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SmsFlashPromotionRepository promotionRepository;

    @Autowired
    private ObjectMapper objectMapper;


    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("根据活动名称分页查询")
    @Test
    public void getFlashPromotionByQueryTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/flash/list")
                .param("keyword","家电")
                .param("pageNum","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        ArrayList obj = objectMapper.readValue(JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data"),ArrayList.class);
        Assertions.assertEquals(2,obj.size());

    }

    @DisplayName("添加活动")
    @Test
    public void createPromotionTest() throws Exception {
        SmsFlashPromotion promotion = new SmsFlashPromotion();
        promotion.setTitle("水瓶座特惠卷");
        promotion.setStartDate(new Date());
        promotion.setEndDate(DateUtil.offsetDay(new Date(),7));
        promotion.setStatus(1);
        promotion.setCreateTime(new Date());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/flash/create")
                .content(objectMapper.writeValueAsString(promotion))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(10,promotionRepository.count());

    }

    @DisplayName("编辑活动")
    @Test
    public void updatePromotionTest() throws Exception {
        SmsFlashPromotion promotion = new SmsFlashPromotion();
        promotion.setTitle("水瓶座特惠卷");
        promotion.setStartDate(new Date());
        promotion.setEndDate(DateUtil.offsetDay(new Date(),7));
        promotion.setStatus(1);
        promotion.setCreateTime(new Date());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/flash/update/13")
                .content(objectMapper.writeValueAsString(promotion))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals("水瓶座特惠卷",promotionRepository.findById(13).get().getTitle());
    }

    @DisplayName("删除活动")
    @Test
    public void deletePromotionTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/flash/delete/13")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(8,promotionRepository.count());
    }

    @DisplayName("修改上下线状态")
    @Test
    public void updatePromotionStatusTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/flash/update/status/13")
                .param("status","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(1,promotionRepository.findById(13).get().getStatus());
    }


    @DisplayName("获取活动详情")
    @Test
    public void getPromotionDetailTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/flash/13")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        SmsFlashPromotion promotion = objectMapper.readValue(JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data"), SmsFlashPromotion.class);
        Assertions.assertNotNull(promotion);
        Assertions.assertEquals(0,promotion.getStatus());
    }

    @DisplayName("分页查询不同场次关联及商品信息")
    @Test
    public void getPromotionProductRelationTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/flashProductRelation/list")
                .param("flashPromotionId","2")
                .param("flashPromotionSessionId","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        ArrayList list = objectMapper.readValue(JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data"), ArrayList.class);
        Assertions.assertEquals(4,list.size());
    }



}
