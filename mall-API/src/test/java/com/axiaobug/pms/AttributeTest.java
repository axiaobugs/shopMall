package com.axiaobug.pms;

import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.pojo.pms.PmsProductAttributeCategory;
import com.axiaobug.repository.pms.PmsProductAttributeCategoryRepository;
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

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@SpringBootTest(classes = MallApiApplication.class)
@Transactional
public class AttributeTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PmsProductAttributeCategoryRepository attributeCategoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("添加商品属性分类")
    @Test
    public void createAttCateTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/productAttribute/category/create")
                .param("name","手机数码-电脑")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(9,attributeCategoryRepository.count());
        PmsProductAttributeCategory name = attributeCategoryRepository.findByName("手机数码-电脑");
        Assertions.assertFalse(name.getName().isEmpty());
    }

    @DisplayName("添加商品属性分类")
    @Test
    public void updateAttCateTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/productAttribute/category/update/10")
                .param("name","SB")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(8,attributeCategoryRepository.count());
        Assertions.assertEquals("SB",attributeCategoryRepository.findById(10).get().getName());
    }

    @DisplayName("添加商品属性分类")
    @Test
    public void deleteAttCateTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/productAttribute/category/delete/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(7,attributeCategoryRepository.count());
        Assertions.assertFalse(attributeCategoryRepository.findById(10).isPresent());
    }


    @DisplayName("获取单个商品属性分类信息")
    @Test
    public void getAttCateTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/productAttribute/category/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(8,attributeCategoryRepository.count());
        Assertions.assertEquals("测试分类",attributeCategoryRepository.findById(10).get().getName());
    }

    @DisplayName("分页获取所有商品属性分类")
    @Test
    public void getAttCateListTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/productAttribute/category/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        List list = objectMapper.readValue(data, List.class);
        Assertions.assertFalse(list.isEmpty());

    }
}
