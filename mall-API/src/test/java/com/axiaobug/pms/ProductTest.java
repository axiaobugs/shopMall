package com.axiaobug.pms;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.dto.PmsProductParam;
import com.axiaobug.dto.PmsProductQueryParam;
import com.axiaobug.pojo.pms.PmsProduct;
import com.axiaobug.repository.pms.PmsBrandRepository;
import com.axiaobug.repository.pms.PmsProductRepository;
import com.axiaobug.repository.pms.PmsProductVertifyRecordRepository;
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

import java.util.ArrayList;
import java.util.List;


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

    @Autowired
    private PmsProductVertifyRecordRepository recordRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("εε»Ίεε")
    @Test
    public void createProductTest() throws Exception {
        PmsProduct param = new PmsProduct();
        param.setBrandId(3);
        param.setProductSerialNumber("No865774788");
        param.setName("εδΈΊ Mate 40");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/product/create")
                .content(objectMapper.writeValueAsString(param))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(30,productRepository.count());
    }

    @DisplayName("ζ Ήζ?εεidθ·εεεηΌθΎδΏ‘ζ―")
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

    @DisplayName("ζ Ήζ?εεidζ΄ζ°εεδΏ‘ζ―")
    @Test
    public void updateProductTest() throws Exception {
        PmsProductParam param = new PmsProductParam();
        param.setBrandId(3);
        param.setProductSerialNumber("No865774788");
        param.setName("εδΈΊ Mate 40");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/product/update/3")
                .content(objectMapper.writeValueAsString(param))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(productRepository.findById(3).get().toString());
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(3,productRepository.findById(3).get().getBrandId());
    }

    @DisplayName("ζ₯θ―’εε")
    @Test
    public void queryProductTest() throws Exception {
        PmsProductQueryParam queryParam = new PmsProductQueryParam();
        queryParam.setBrandId(1);
        queryParam.setProductCategoryId(7);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/product/list")
                .content(objectMapper.writeValueAsString(queryParam))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        Assertions.assertEquals(5,objectMapper.readValue(data, ArrayList.class).size());
    }

    @DisplayName("ζ Ήζ?εεεη§°ζθ΄§ε·ζ¨‘η³ζ₯θ―’")
    @Test
    public void queryProductByKeywordTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/product/simpleList")
                .param("keyword","865")
                .param("field","sn")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        Assertions.assertEquals(15,objectMapper.readValue(data, ArrayList.class).size());
    }

    @DisplayName("ζΉιδΏ?ζΉε?‘ζ ΈηΆζ")
    @Test
    public void editVerifyStatusByBatchTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/product/update/verifyStatus")
                .param("ids","33,34,35")
                .param("verifyStatus","1")
                .param("detail","ε³η³»ζ·,ζ²‘ζεζ³εͺθ½ιθΏ")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        Assertions.assertEquals(5,recordRepository.count());
        Assertions.assertEquals(1,productRepository.findById(33).get().getVerifyStatus());
    }

}
