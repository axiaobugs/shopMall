package com.axiaobug.sms;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
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


    @DisplayName("添加优惠券")
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

    @DisplayName("删除优惠券")
    @Test
    public void deleteCouponTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/coupon/delete/22")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(13,couponRepository.count());
    }

    @DisplayName("修改优惠券")
    @Test
    public void updateCouponTest() throws Exception {
        SmsCoupon coupon = couponRepository.findById(22).get();
        coupon.setName("老板亲戚优惠卷");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/coupon/update/22")
                .content(objectMapper.writeValueAsString(coupon))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals("老板亲戚优惠卷",couponRepository.findById(22).get().getName());
    }
}
