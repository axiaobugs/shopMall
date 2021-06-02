package com.axiaobug.pms;

import cn.hutool.json.JSONUtil;
import com.axiaobug.MallApiApplication;
import com.axiaobug.dto.OmsReceiverInfoParam;
import com.axiaobug.pojo.oms.OmsOrder;
import com.axiaobug.pojo.pms.PmsSkuStock;
import com.axiaobug.repository.pms.PmsSkuStockRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@SpringBootTest(classes = MallApiApplication.class)
@Transactional
public class PmsSkuTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PmsSkuStockRepository pmsSkuStockRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @DisplayName("根据商品编号及sku编码模糊搜索sku库存")
    @Test
    public void getSkuListTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/sku/27")
                .param("keyword","黑色")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        String data = JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("data");
        List list = objectMapper.readValue(data, List.class);
        Assertions.assertEquals(2,list.size());
    }

    @DisplayName("批量更新sku库存信息(sku_code单独一个模块更改)")
    @Test
    public void editReceiverTest() throws Exception {
        ArrayList<PmsSkuStock> skuStocks = new ArrayList<>();
        PmsSkuStock sku = new PmsSkuStock();
        sku.setSkuCode("201808270027001");
        sku.setPrice(BigDecimal.valueOf(8888));
        sku.setProductId(27);
        skuStocks.add(sku);

        String requestBody = objectMapper.writeValueAsString(skuStocks);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .patch("/sku/update/27")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andReturn();
        Assertions.assertEquals("200", JSONUtil.parseObj(mvcResult.getResponse().getContentAsString()).getStr("code"));
        PmsSkuStock skuStock = pmsSkuStockRepository.findByIdAndSkuCode(98, "201808270027001");
        Assertions.assertEquals(BigDecimal.valueOf(8888),skuStock.getPrice());
    }
}
