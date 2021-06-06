package com.axiaobug.pms;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.dto.PmsBrandParam;
import com.axiaobug.pojo.pms.PmsBrand;
import com.axiaobug.repository.pms.PmsBrandRepository;
import com.axiaobug.repository.pms.PmsProductCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
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
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@SpringBootTest(classes = MallApiApplication.class)
@Transactional
public class BrandTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PmsBrandRepository brandRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("获取全部品牌列表")
    @Test
    public void getBranListAllTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/brand/listAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        Assertions.assertEquals(11,JSONUtil.parseArray(data).size());
    }


    @DisplayName("添加品牌")
    @Test
    public void createBranTest() throws Exception {
        PmsBrandParam param = new PmsBrandParam();
        param.setName("SONY");
        param.setFirstLetter("S");
        param.setSort(100);
        param.setFactoryStatus(1);
        param.setShowStatus(1);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/brand/create")
                .content(objectMapper.writeValueAsString(param))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        Assertions.assertEquals(12,brandRepository.count());
    }

    @DisplayName("添加品牌")
    @Test
    public void updateBranTest() throws Exception {
        PmsBrandParam param = new PmsBrandParam();
        param.setName("SONY");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/brand/update/1")
                .content(objectMapper.writeValueAsString(param))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        Assertions.assertEquals(11,brandRepository.count());
        Assertions.assertEquals("SONY",brandRepository.findById(1).get().getName());
    }

    @DisplayName("删除品牌")
    @Test
    public void deleteBranTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/brand/delete/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(10,brandRepository.count());
    }

    @DisplayName("根据品牌名称分页获取品牌列表")
    @Test
    public void deleteBranBatchTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/brand/list")
                .param("keyword","三星")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        final String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        final JSONArray array = JSONUtil.parseArray(data);
        Assertions.assertEquals(1,array.size());
    }

    @DisplayName("批量删除品牌")
    @Test
    public void getBranByKeywordTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/brand/delete/batch")
                .param("ids","2,4,5")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(8,brandRepository.count());
    }

    @DisplayName("批量更新显示状态")
    @Test
    public void updateBrandShowStatusBatchTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/brand/update/showStatus")
                .param("ids","3,4,5")
                .param("showStatus","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        Assertions.assertEquals(1,brandRepository.findById(3).get().getShowStatus());
        Assertions.assertEquals(1,brandRepository.findById(4).get().getShowStatus());
        Assertions.assertEquals(1,brandRepository.findById(5).get().getShowStatus());
    }

}
