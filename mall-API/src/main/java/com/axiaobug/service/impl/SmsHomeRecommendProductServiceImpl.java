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
import java.util.List;

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
        return commonMethod.createWithList(homeRecommendProductList,recommendProductRepository);
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
        return commonMethod.deleteWithList(ids,recommendProductRepository);
    }

    @Override
    public Boolean updateRecommendStatus(List<Integer> ids, Integer recommendStatus) throws NoSuchMethodException {
        return commonMethod.updateStatusWithList(ids,recommendStatus,recommendProductRepository);
    }

    @Override
    public List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        if (productName==null && recommendStatus == null){
            return recommendProductRepository.findAll(pageable).getContent();
        }else {
            Specification<SmsHomeRecommendProduct> specification = commonMethod.createSpecification(productName,recommendStatus);
            return recommendProductRepository.findAll(specification,pageable).getContent();
        }
    }
}
