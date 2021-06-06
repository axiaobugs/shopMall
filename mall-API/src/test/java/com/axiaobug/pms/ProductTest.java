package com.axiaobug.pms;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.dto.PmsProductParam;
import com.axiaobug.pojo.pms.PmsProduct;
import com.axiaobug.repository.pms.PmsBrandRepository;
import com.axiaobug.repository.pms.PmsProductRepository;
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
 * @date 06 2021
 */
@SpringBootTest(classes = MallApiApplication.class)
@Transactional
public class ProductTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PmsProductRepository productRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("创建商品")
    @Test
    public void createProductTest() throws Exception {
        PmsProduct param = new PmsProduct();
        param.setBrandId(3);
        param.setProductSerialNumber("No865774788");
        param.setName("华为 Mate 40");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/product/create")
                .content(objectMapper.writeValueAsString(param))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(30,productRepository.count());
    }

    @DisplayName("根据商品id获取商品编辑信息")
    @Test
    public void getProductByIdTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/product/updateInfo/27")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        Assertions.assertEquals("2",JSONUtil.parseObj(data).getStr("cateParentId"));
    }

    @DisplayName("根据商品id更新商品信息")
    @Test
    public void updateProductTest() throws Exception {
        PmsProduct param = new PmsProduct();
        param.setBrandId(3);
        param.setProductSerialNumber("No865774788");
        param.setName("华为 Mate 40");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/product/update/3")
                .content(objectMapper.writeValueAsString(param))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(productRepository.findById(3).get().toString());
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(3,productRepository.findById(3).get().getBrandId());
    }



}
