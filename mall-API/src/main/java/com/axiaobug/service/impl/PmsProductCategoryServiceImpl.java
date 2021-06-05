package com.axiaobug.service.impl;


import com.axiaobug.common.CommonMethod;
import com.axiaobug.dto.PmsProductCategoryParam;
import com.axiaobug.dto.PmsProductCategoryWithChildrenItem;
import com.axiaobug.pojo.pms.PmsProductCategory;
import com.axiaobug.repository.pms.PmsProductCategoryRepository;
import com.axiaobug.service.PmsProductCategoryService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


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

    public PmsProductCategoryServiceImpl(PmsProductCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private final PmsProductCategoryRepository categoryRepository;

    @Override
    public Boolean create(PmsProductCategoryParam pmsProductCategoryParam) throws Exception {
        PmsProductCategory productCategory = new PmsProductCategory();
        Boolean isNull = commonMethod.setParamToTarget(pmsProductCategoryParam, productCategory);

        if (!isNull){
            this.categoryRepository.save(productCategory);
            return true;
        }else {
            return false;
        }
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
