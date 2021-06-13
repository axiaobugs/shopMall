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
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class SmsHomeNewProductServiceImpl implements SmsHomeNewProductService {

    @Resource
    private  SmsHomeNewProductRepository productRepository;

    @Resource
    private  CommonMethod commonMethod;


    @Override
    public Boolean create(List<SmsHomeNewProduct> homeNewProductList) throws Exception {
        return commonMethod.createWithList(homeNewProductList,productRepository);
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
        return commonMethod.deleteWithList(ids,productRepository);
    }

    @Override
    public Boolean updateRecommendStatus(List<Integer> ids, Integer recommendStatus) throws Exception {
        return commonMethod.updateStatusWithList(ids,recommendStatus,productRepository);
    }

    @Override
    public List<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        if (productName==null && recommendStatus == null){
            return productRepository.findAll(pageable).getContent();
        }else {
            Specification<SmsHomeNewProduct> specification = commonMethod.createSpecification(productName,recommendStatus);
            return productRepository.findAll(specification,pageable).getContent();
        }
    }


}
