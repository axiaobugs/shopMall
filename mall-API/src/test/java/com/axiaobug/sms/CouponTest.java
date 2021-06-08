package com.axiaobug.sms;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.dto.PmsProductParam;
import com.axiaobug.dto.SmsCouponParam;
import com.axiaobug.pojo.pms.PmsProduct;
import com.axiaobug.pojo.sms.SmsCoupon;
import com.axiaobug.pojo.sms.SmsCouponProductRelation;
import com.axiaobug.repository.pms.PmsProductRepository;
import com.axiaobug.repository.sms.SmsCouponProductRelationRepository;
import com.axiaobug.repository.sms.SmsCouponRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
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
public class CouponTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SmsCouponRepository couponRepository;

    @Autowired
    private SmsCouponProductRelationRepository productRelationRepository;

    @Autowired
    private PmsProductRepository productRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("添加优惠券(没有List)")
    @Test
    public void createCouponTest() throws Exception {
        SmsCouponParam param = new SmsCouponParam();
        param.setName("老板亲戚优惠卷");
        param.setStartTime(new Date());
        param.setEndTime(DateUtil.offsetDay(new Date(),7));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/coupon/create")
                .content(objectMapper.writeValueAsString(param))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(15,couponRepository.count());
    }

    @DisplayName("添加优惠券(没有List)")
    @Test
    public void createCouponWithRelationTest() throws Exception {
        ArrayList<SmsCouponProductRelation> smsCouponProductRelations = new ArrayList<>();
        SmsCouponProductRelation relation = new SmsCouponProductRelation();
        relation.setCouponId(4);
        relation.setProductId(26);
        relation.setProductName(productRepository.findById(26).get().getName());
        relation.setProductSerialNum(productRepository.findById(26).get().getProductSerialNumber());
        smsCouponProductRelations.add(relation);


        SmsCoupon param = new SmsCoupon();
        param.setName("老板亲戚优惠卷");
        param.setStartTime(new Date());
        param.setEndTime(DateUtil.offsetDay(new Date(),7));
        param.setCouponProductRelations(smsCouponProductRelations);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/coupon/create")
                .content(objectMapper.writeValueAsString(param))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(15,couponRepository.count());
        Assertions.assertEquals(2,productRelationRepository.count());
        productRelationRepository.findAll().forEach(System.out::println);
    }
}
