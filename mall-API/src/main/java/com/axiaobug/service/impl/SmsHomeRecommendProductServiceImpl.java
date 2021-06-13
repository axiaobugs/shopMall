package com.axiaobug.service.impl;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.pojo.sms.SmsHomeRecommendProduct;
import com.axiaobug.repository.sms.SmsHomeRecommendProductRepository;
import com.axiaobug.service.SmsHomeRecommendProductService;
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
public class SmsHomeRecommendProductServiceImpl implements SmsHomeRecommendProductService {

    @Resource
    private SmsHomeRecommendProductRepository recommendProductRepository;

    @Resource
    private CommonMethod commonMethod;

    @Override
    public Boolean create(List<SmsHomeRecommendProduct> homeRecommendProductList) throws Exception {
        if (!homeRecommendProductList.isEmpty()){
            AtomicInteger atomicInteger = new AtomicInteger();
            homeRecommendProductList.forEach(recommendProduct->{
                if (!commonMethod.isEmptyObject(recommendProduct)){
                    try {
                        recommendProductRepository.save(recommendProduct);
                        atomicInteger.incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return atomicInteger.get()==homeRecommendProductList.size();
        }
        throw new Exception("提交数组为空,请检查后,重新提交");
    }

    @Override
    public Boolean updateSort(Integer id, Integer sort) {
        if (id==null || sort==null ){
            return false;
        }
        if (recommendProductRepository.findById(id).isPresent()){
            SmsHomeRecommendProduct recommendProduct = recommendProductRepository.findById(id).get();
            recommendProduct.setSort(sort);
            try {
                recommendProductRepository.save(recommendProduct);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Boolean delete(List<Integer> ids) throws Exception {
        if (!ids.isEmpty()){
            AtomicInteger atomicInteger = new AtomicInteger();
            ids.forEach(id->{
                if (recommendProductRepository.existsById(id)){
                    try {
                        recommendProductRepository.deleteById(id);
                        atomicInteger.incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return atomicInteger.get()==ids.size();
        }
        throw new Exception(",请检查后,重新提交");
    }

    @Override
    public Boolean updateRecommendStatus(List<Integer> ids, Integer recommendStatus) {
        if (ids.isEmpty() || recommendStatus==null){
            return false;
        }else{
            AtomicInteger atomicInteger = new AtomicInteger();
            ids.forEach(id->{
                if (recommendProductRepository.findById(id).isPresent()){
                    SmsHomeRecommendProduct recommendProduct = recommendProductRepository.findById(id).get();
                    recommendProduct.setRecommendStatus(recommendStatus);
                    try {
                        recommendProductRepository.save(recommendProduct);
                        atomicInteger.incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return atomicInteger.get()==ids.size();
        }
    }

    @Override
    public List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        if (productName==null && recommendStatus == null){
            return recommendProductRepository.findAll(pageable).getContent();
        }else {
            Specification<SmsHomeRecommendProduct> specification = (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (productName!=null){
                    predicates.add(criteriaBuilder.like(root.get("productName").as(String.class),"%"+productName+"%"));
                }
                if (recommendStatus!=null){
                    predicates.add(criteriaBuilder.equal(root.get("recommendStatus").as(Integer.class),recommendStatus));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            };
            return recommendProductRepository.findAll(specification,pageable).getContent();
        }
    }
}
