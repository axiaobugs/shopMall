package com.axiaobug.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.axiaobug.common.CommonMethod;
import com.axiaobug.dto.PmsProductCategoryParam;
import com.axiaobug.dto.PmsProductCategoryWithChildrenItem;
import com.axiaobug.pojo.pms.PmsProductCategory;
import com.axiaobug.repository.pms.PmsProductCategoryRepository;
import com.axiaobug.service.PmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * management of product category service implementation class
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {

    @Resource
    private CommonMethod commonMethod;

    @Autowired
    private PmsProductCategoryRepository categoryRepository;

    @Override
    public Boolean create(PmsProductCategoryParam pmsProductCategoryParam) throws Exception {
        PmsProductCategory productCategory = new PmsProductCategory();
        HashMap<Object, Object> hashMap = commonMethod.gainConditionFromObjectByField(pmsProductCategoryParam);
        if (MapUtil.isNotEmpty(hashMap)) {
            for (Map.Entry<Object,Object> entry: hashMap.entrySet()){
                String setMethodName = "set"+entry.getKey().toString();
                ReflectUtil.invoke(productCategory,setMethodName,entry.getValue());
            }

            // Determine productCategory is null
            boolean productFieldsAllNull = MapUtil.isEmpty(commonMethod.gainConditionFromObjectByField(productCategory));
            if (!productFieldsAllNull){
                categoryRepository.save(productCategory);
                return true;
            }else {
                return false;
            }
        }
        throw new Exception("param is null");
    }

    @Override
    public int update(Integer id, PmsProductCategoryParam pmsProductCategoryParam) {
        return 0;
    }

    @Override
    public List<PmsProductCategory> getList(Integer parentId, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public PmsProductCategory getItem(Integer id) {
        return null;
    }

    @Override
    public int updateNavStatus(List<Integer> ids, Integer navStatus) {
        return 0;
    }

    @Override
    public int updateShowStatus(List<Integer> ids, Integer showStatus) {
        return 0;
    }

    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        return null;
    }
}
