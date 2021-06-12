package com.axiaobug.sms;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.pojo.sms.SmsHomeAdvertise;
import com.axiaobug.pojo.sms.SmsHomeBrand;
import com.axiaobug.repository.sms.SmsHomeAdvertiseRepository;
import com.axiaobug.repository.sms.SmsHomeBrandRepository;
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
public class CarouselAndBrandTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SmsHomeAdvertiseRepository advertiseRepository;

    @Autowired
    private SmsHomeBrandRepository brandRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("添加活动场次")
    @Test
    public void createCarouselTest() throws Exception {
        SmsHomeAdvertise advertise = new SmsHomeAdvertise();
        advertise.setName("川普下大促销");
        advertise.setStartTime(new Date());
        advertise.setEndTime(DateUtil.offsetDay(new Date(),7));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/home/advertise/create")
                .content(objectMapper.writeValueAsString(advertise))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(7,advertiseRepository.count());

    }

    @DisplayName("批量删除广告")
    @Test
    public void deleteCarouselBatchTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/home/advertise/delete")
                .param("ids","2,3,4")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(3,advertiseRepository.count());

    }

    @DisplayName("分页查询广告")
    @Test
    public void getCarouselByQueryTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/home/advertise/list")
                .param("name","夏季")
                .param("endTime","2018-11-15")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        ArrayList list = objectMapper.readValue(data, ArrayList.class);
        Assertions.assertEquals(1,list.size());

    }

    @DisplayName("添加首页推荐品牌")
    @Test
    public void createHomeBrandTest() throws Exception {
        ArrayList<SmsHomeBrand> smsHomeBrands = new ArrayList<>();
        SmsHomeBrand homeBrand = new SmsHomeBrand();
        homeBrand.setBrandId(99);
        homeBrand.setBrandName("SONY");
        homeBrand.setRecommendStatus(1);
        smsHomeBrands.add(homeBrand);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/home/brand/create")
                .content(objectMapper.writeValueAsString(smsHomeBrands))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(14,brandRepository.count());

    }

    @DisplayName("分页查询推荐品牌")
    @Test
    public void getBrandByQueryTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/home/brand/list")
                .param("brandName","三星")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        ArrayList list = objectMapper.readValue(data, ArrayList.class);
        Assertions.assertEquals(2,list.size());

    }


}
