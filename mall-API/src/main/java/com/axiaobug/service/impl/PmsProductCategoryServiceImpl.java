package com.axiaobug.service.impl;


import com.axiaobug.common.CommonMethod;
import com.axiaobug.dto.PmsProductCategoryParam;
import com.axiaobug.dto.PmsProductCategoryWithChildrenItem;
import com.axiaobug.pojo.pms.PmsProductCategory;
import com.axiaobug.repository.pms.PmsProductCategoryRepository;
import com.axiaobug.service.PmsProductCategoryService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
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
    public Boolean update(Integer id, PmsProductCategoryParam pmsProductCategoryParam) throws Exception {
        if (this.categoryRepository.findById(id).isPresent()) {
            PmsProductCategory source = this.categoryRepository.findById(id).get();
            Boolean isNull = commonMethod.setParamToTarget(pmsProductCategoryParam, source);
            if (!isNull){
                this.categoryRepository.save(source);
                return true;
            }
            return false;
        }
        throw new Exception("Product Category update failure,please check!");
    }

    @Override
    public List<PmsProductCategory> getList(Integer parentId, Integer pageSize, Integer pageNum) {
        Pageable pageable =  PageRequest.of(pageNum,pageSize);
        PmsProductCategory category = new PmsProductCategory();
        category.setParentId(parentId);
        Example<PmsProductCategory> example = Example.of(category);
        System.out.println(categoryRepository.findAll(example,pageable).getContent());
        return categoryRepository.findAll(example,pageable).getContent();


    }

    @Override
    public Boolean delete(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PmsProductCategory getItem(Integer id) {
        return categoryRepository.findById(id).orElse(new PmsProductCategory());
    }

    @Override
    public Boolean updateNavStatus(List<Integer> ids, Integer navStatus) throws Exception {
        return updateNavOrShowStatus(ids,navStatus,"nav");

    }

    @Override
    public Boolean updateShowStatus(List<Integer> ids, Integer showStatus) throws Exception {
        System.out.println(showStatus);
        return updateNavOrShowStatus(ids,showStatus,"show");
    }

    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() throws Exception {
        PmsProductCategory productCategory = new PmsProductCategory();
        ArrayList<PmsProductCategoryWithChildrenItem> res = new ArrayList<>();
        productCategory.setLevel(0);
        Example<PmsProductCategory> example = Example.of(productCategory);
        List<PmsProductCategory> all = categoryRepository.findAll(example);
        all.forEach(category->{
            Integer categoryId = category.getId();
            // 新建一个实例,用来放进结果数组
            // new instance for covert parent class to child class
            PmsProductCategoryWithChildrenItem source = new PmsProductCategoryWithChildrenItem();
            // 赋值所有非空属性到子类中
            // clone all not null fields to child class
            Boolean flag = commonMethod.setParamToTarget(category, source);
            if (flag) {
                // 实例是用来做example用.
                // this instance for make example
                PmsProductCategory exampleChild = new PmsProductCategory();
                exampleChild.setParentId(categoryId);
                List<PmsProductCategory> childCategory = categoryRepository.findAll(Example.of(exampleChild));
                source.setChildren(childCategory);
                res.add(source);
            }
        });
        if (res.size()== all.size()){
            return res;
        }
        throw new Exception("Some thing wrong when fetch category and child category");
    }


    private Boolean updateNavOrShowStatus(List<Integer> ids, Integer status, String method) throws Exception {
        if (ids.isEmpty()){
            throw new Exception("传入的id数组是空的,检查传值");
        }
        ArrayList<Object> res = new ArrayList<>(ids.size());
        ids.forEach(id->{
            boolean flag = categoryRepository.findById(id).isPresent();
            if (flag){
                PmsProductCategory category = categoryRepository.findById(id).get();
                if ("nav".equals(method)){
                    category.setNavStatus(status);
                }else if ("show".equals(method)){
                    category.setShowStatus(status);
                }else {
                    res.add(1);
                }
                try {
                    categoryRepository.save(category);
                } catch (Exception e) {
                    e.printStackTrace();
                    res.add(1);
                }
            }else {
                res.add(1);
            }

        });
        return res.isEmpty();
    }
}
