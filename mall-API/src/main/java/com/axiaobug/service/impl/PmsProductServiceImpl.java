package com.axiaobug.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.axiaobug.common.CommonMethod;
import com.axiaobug.dto.PmsProductParam;
import com.axiaobug.dto.PmsProductQueryParam;
import com.axiaobug.dto.PmsProductResult;
import com.axiaobug.pojo.pms.PmsProduct;
import com.axiaobug.repository.pms.PmsProductCategoryRepository;
import com.axiaobug.repository.pms.PmsProductRepository;
import com.axiaobug.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class PmsProductServiceImpl implements PmsProductService {
    private static String QUERY_NAME = "name";
    private static String QUERY_SN = "sn";

    @Autowired
    private PmsProductRepository productRepository;

    @Autowired
    private PmsProductCategoryRepository categoryRepository;

    @Autowired
    private CommonMethod commonMethod;

    @Override
    public int create(PmsProduct product) {
        final long count1 = productRepository.count();
        productRepository.save(product);
        final long count2 = productRepository.count();
        return (int) (count2-count1);
    }

    /*
    TODO: 增加空指针判断.
    */

    @Override
    public PmsProductResult getUpdateInfo(Integer id) {
        if (productRepository.findById(id).isPresent()) {
            PmsProductResult source = new PmsProductResult();
            PmsProduct target = productRepository.findById(id).get();
            commonMethod.setParamToTarget(target, source);
            Integer categoryId = target.getProductCategoryId();
            source.setCateParentId(categoryRepository.findById(categoryId).get().getParentId());
            return source;
        }
        return null;
    }

    @Override
    public int update(Integer id, PmsProductParam productParam) {
        if (productRepository.findById(id).isPresent()) {
            PmsProduct source = productRepository.findById(id).get();
            Boolean flag = commonMethod.setParamToTarget(productParam, source);
            productRepository.save(source);
            return 1;
        }
        return 0;
    }

    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        PmsProduct source = new PmsProduct();
        Boolean flag = commonMethod.setParamToTarget(productQueryParam, source);
        if (flag){
            Example<PmsProduct> example = Example.of(source);
            return productRepository.findAll(example,pageable).getContent();
        }
        return null;
    }

    @Override
    public List<PmsProduct> list(String keyword,String field) {
        Specification<PmsProduct> specification = (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = null;
            if (field.equals(QUERY_NAME)){
                predicate = criteriaBuilder.like(root.get("name").as(String.class),"%"+keyword+"%");
            }
            if (field.equals(QUERY_SN)){
                predicate = criteriaBuilder.like(root.get("productSerialNumber").as(String.class),"%"+keyword+"%");
            }
            return criteriaBuilder.and(predicate);
        };
        return productRepository.findAll(specification);
    }

    @Override
    public int updateVerifyStatus(List<Integer> ids, Integer verifyStatus, String detail) {
        return 0;
    }

    @Override
    public int updatePublishStatus(List<Integer> ids, Integer publishStatus) {
        return 0;
    }

    @Override
    public int updateRecommendStatus(List<Integer> ids, Integer recommendStatus) {
        return 0;
    }

    @Override
    public int updateDeleteStatus(List<Integer> ids, Integer deleteStatus) {
        return 0;
    }

    @Override
    public List<PmsProduct> list(String keyword) {
        return null;
    }
}
