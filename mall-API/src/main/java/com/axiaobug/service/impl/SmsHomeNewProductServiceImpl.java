package com.axiaobug.service.impl;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.pojo.sms.SmsHomeNewProduct;
import com.axiaobug.repository.sms.SmsHomeNewProductRepository;
import com.axiaobug.service.SmsHomeNewProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class SmsHomeNewProductServiceImpl implements SmsHomeNewProductService {

    @Resource
    private SmsHomeNewProductRepository productRepository;

    @Resource
    private CommonMethod commonMethod;

    @Override
    public Boolean create(List<SmsHomeNewProduct> homeNewProductList) throws Exception {
        if (!homeNewProductList.isEmpty()){
            AtomicInteger atomicInteger = new AtomicInteger();
            homeNewProductList.forEach(product->{
                try {
                    productRepository.save(product);
                    atomicInteger.incrementAndGet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return atomicInteger.get()==homeNewProductList.size();
        }
        throw new Exception("提交的数组为空,请检查后重试,操作已回滚");
    }

    @Override
    public Boolean updateSort(Integer id, Integer sort) throws Exception {
        if (productRepository.findById(id).isPresent() && sort!= null){
            SmsHomeNewProduct newProduct = productRepository.findById(id).get();
            try {
                productRepository.save(newProduct);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("Id 不在数据库或者排名参数为空,请检查后重试,操作已回滚");
    }

    @Override
    public Boolean delete(List<Integer> ids) throws Exception {
        if (!ids.isEmpty()){
            AtomicInteger atomicInteger = new AtomicInteger();
            ids.forEach(id->{
                if (productRepository.existsById(id)) {
                    try {
                        productRepository.deleteById(id);
                        atomicInteger.incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return atomicInteger.get()==ids.size();
        }
        throw new Exception("提交的数组为空,请检查后重试,操作已回滚");
    }

    @Override
    public Boolean updateRecommendStatus(List<Integer> ids, Integer recommendStatus) throws Exception {
        if (!ids.isEmpty()&& recommendStatus!=null){
            AtomicInteger atomicInteger = new AtomicInteger();
            ids.forEach(id->{
                if (productRepository.findById(id).isPresent()) {
                    SmsHomeNewProduct homeNewProduct = productRepository.findById(id).get();
                    homeNewProduct.setRecommendStatus(recommendStatus);
                    try {
                        productRepository.save(homeNewProduct);
                        atomicInteger.incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return atomicInteger.get()==ids.size();
        }
        throw new Exception("提交的数组为空或推荐状态为空,请检查后重试,操作已回滚");
    }

    @Override
    public List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        if (productName==null && recommendStatus == null){
            return productRepository.findAll(pageable).getContent();
        }else {
            System.out.println(productName);
            Specification<SmsHomeNewProduct> specification = (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (productName!=null){
                    predicates.add(criteriaBuilder.like(root.get("productName").as(String.class),"%"+productName+"%"));
                }
                if (recommendStatus!=null){
                    predicates.add(criteriaBuilder.equal(root.get("recommendStatus").as(Integer.class),recommendStatus));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            };
            return productRepository.findAll(specification,pageable).getContent();
        }
    }
}
