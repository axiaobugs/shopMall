package com.axiaobug.pms;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.dto.PmsProductCategoryParam;
import com.axiaobug.repository.pms.PmsProductCategoryRepository;
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
public class CategoryTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PmsProductCategoryRepository productCategoryRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("添加产品分类")
    @Test
    public void createCategoryTest() throws Exception {
        PmsProductCategoryParam param = new PmsProductCategoryParam();
        param.setParentId(2);
        param.setName("电竞台式机");
        param.setKeywords("华硕");


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/productCategory/create")
                .content(objectMapper.writeValueAsString(param))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(37,productCategoryRepository.count());
    }

    @DisplayName("修改商品分类")
    @Test
    public void updateCategoryTest() throws Exception {
        PmsProductCategoryParam param = new PmsProductCategoryParam();
        param.setParentId(2);
        param.setName("电竞台式机");
        param.setKeywords("华硕");


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/productCategory/update/11")
                .content(objectMapper.writeValueAsString(param))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(36,productCategoryRepository.count());
        Assertions.assertEquals(2,productCategoryRepository.findById(11).get().getParentId());
    }

    @DisplayName("分页查询商品分类")
    @Test
    public void getCategoryByParentIdTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/productCategory/list/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        Assertions.assertEquals(5,JSONUtil.parseArray(data).size());
    }

    @DisplayName("分页查询商品分类")
    @Test
    public void getCategoryByIdTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/productCategory/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        Assertions.assertEquals("1",JSONUtil.parseObj(data).getStr("navStatus"));
    }

    @DisplayName("删除商品分类")
    @Test
    public void deleteCategoryByIdTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/productCategory/delete/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(35,productCategoryRepository.count());
    }

    @DisplayName("批量修改导航栏显示状态")
    @Test
    public void updateCategoryNavTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/productCategory/update/navStatus")
                .param("ids","7,8,9")
                .param("navStatus","0")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(0,productCategoryRepository.findById(7).get().getNavStatus());
        Assertions.assertEquals(0,productCategoryRepository.findById(8).get().getNavStatus());
        Assertions.assertEquals(0,productCategoryRepository.findById(9).get().getNavStatus());
    }

    @DisplayName("批量修改显示状态")
    @Test
    public void updateCategoryShowTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/productCategory/update/showStatus")
                .param("ids","7,8,9")
                .param("showStatus","0")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(0,productCategoryRepository.findById(7).get().getShowStatus());
        Assertions.assertEquals(0,productCategoryRepository.findById(8).get().getShowStatus());
        Assertions.assertEquals(0,productCategoryRepository.findById(9).get().getShowStatus());
    }

    @DisplayName("批量修改显示状态")
    @Test
    public void getCategoryWithChildTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/productCategory/list/withChildren")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        Assertions.assertEquals(5,JSONUtil.parseArray(data).toArray().length);
    }
}
