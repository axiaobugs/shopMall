package com.axiaobug.sms;

import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.pojo.sms.SmsHomeRecommendProduct;
import com.axiaobug.pojo.sms.SmsHomeRecommendSubject;
import com.axiaobug.repository.sms.SmsHomeRecommendProductRepository;
import com.axiaobug.repository.sms.SmsHomeRecommendSubjectRepository;
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

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@SpringBootTest(classes = MallApiApplication.class)
@Transactional
public class RecommendTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SmsHomeRecommendProductRepository recommendProductRepository;

    @Autowired
    private SmsHomeRecommendSubjectRepository subjectRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("批量添加首页推荐")
    @Test
    public void createRecommendProductTest() throws Exception {
        ArrayList<SmsHomeRecommendProduct> smsHomeNewProducts = new ArrayList<>();
        SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
        recommendProduct.setProductId(27);
        recommendProduct.setProductName("SONY");
        recommendProduct.setRecommendStatus(1);
        smsHomeNewProducts.add(recommendProduct);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/home/recommendProduct/create")
                .content(objectMapper.writeValueAsString(smsHomeNewProducts))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(6,recommendProductRepository.count());

    }

    @DisplayName("批量添加首页推荐主题")
    @Test
    public void createRecommendSubjectTest() throws Exception {
        ArrayList<SmsHomeRecommendSubject> smsHomeNewProducts = new ArrayList<>();
        SmsHomeRecommendSubject recommendProduct = new SmsHomeRecommendSubject();
        recommendProduct.setSubjectId(27);
        recommendProduct.setSubjectName("SONY就是信仰");
        recommendProduct.setRecommendStatus(1);
        smsHomeNewProducts.add(recommendProduct);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/home/recommendSubject/create")
                .content(objectMapper.writeValueAsString(smsHomeNewProducts))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(7,subjectRepository.count());

    }

    @DisplayName("修改推荐排序")
    @Test
    public void updateRecommendProductSortTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/home/recommendProduct/update/sort/3")
                .param("sort","100")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(100,recommendProductRepository.findById(3).get().getSort());
    }

    @DisplayName("修改推荐主题排序")
    @Test
    public void updateRecommendSubjectSortTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/home/recommendSubject/update/sort/14")
                .param("sort","100")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(100,subjectRepository.findById(14).get().getSort());
    }

    @DisplayName("批量删除推荐")
    @Test
    public void deleteRecommendProductBatchTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/home/recommendProduct/delete")
                .param("ids","3,4,5")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(2,recommendProductRepository.count());
    }

    @DisplayName("批量删除推荐主题")
    @Test
    public void deleteRecommendSubjectBatchTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/home/recommendSubject/delete")
                .param("ids","14,15,16")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(3,subjectRepository.count());
    }

    @DisplayName("批量修改推荐状态")
    @Test
    public void updateRecommendProductStatusTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/home/recommendProduct/update/recommendStatus")
                .param("ids","3,4,5")
                .param("status","0")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(0,recommendProductRepository.findById(3).get().getRecommendStatus());
    }

    @DisplayName("批量修改主题推荐状态")
    @Test
    public void updateRecommendSubjectStatusTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/home/recommendSubject/update/recommendStatus")
                .param("ids","14,15,16")
                .param("status","0")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(0,subjectRepository.findById(14).get().getRecommendStatus());
    }

    @DisplayName("分页查询推荐")
    @Test
    public void getRecommendProductByQueryTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/home/recommendProduct/list")
                .param("productName","小米")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        ArrayList list = objectMapper.readValue(data, ArrayList.class);
        Assertions.assertEquals(2,list.size());
    }

    @DisplayName("分页查询推荐主题")
    @Test
    public void getRecommendSubjectByQueryTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/home/recommendSubject/list")
                .param("subjectName","夏")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        ArrayList list = objectMapper.readValue(data, ArrayList.class);
        Assertions.assertEquals(2,list.size());
    }




}
