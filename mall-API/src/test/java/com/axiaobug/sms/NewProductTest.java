package com.axiaobug.sms;

import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.pojo.sms.SmsHomeNewProduct;
import com.axiaobug.repository.sms.SmsHomeNewProductRepository;
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
public class NewProductTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SmsHomeNewProductRepository productRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("添加首页新品")
    @Test
    public void createHomeBrandTest() throws Exception {
        ArrayList<SmsHomeNewProduct> smsHomeNewProducts = new ArrayList<>();
        SmsHomeNewProduct homeProduct = new SmsHomeNewProduct();
        homeProduct.setProductId(27);
        homeProduct.setProductName("SONY");
        homeProduct.setRecommendStatus(1);
        smsHomeNewProducts.add(homeProduct);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/home/newProduct/create")
                .content(objectMapper.writeValueAsString(smsHomeNewProducts))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(7,productRepository.count());

    }

    @DisplayName("分页查询首页新品")
    @Test
    public void getProductByQueryTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/home/newProduct/list")
                .param("productName","小米")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        ArrayList list = objectMapper.readValue(data, ArrayList.class);
        Assertions.assertEquals(3,list.size());

    }


}
