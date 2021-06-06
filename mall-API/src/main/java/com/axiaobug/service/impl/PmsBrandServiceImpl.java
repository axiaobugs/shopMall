package com.axiaobug.service.impl;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.dto.PmsBrandParam;
import com.axiaobug.pojo.pms.PmsBrand;
import com.axiaobug.repository.pms.PmsBrandRepository;
import com.axiaobug.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * management product brand Service impl
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    private final static String SHOW_METHOD = "show";
    private final static String FACTORY_METHOD = "factory";



    @Resource
    private PmsBrandRepository brandRepository;

    @Autowired
    private CommonMethod commonMethod;


    @Override
    public List<PmsBrand> listAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public Boolean createBrand(PmsBrandParam pmsBrandParam) {
        PmsBrand source = new PmsBrand();
        Boolean flag = commonMethod.setParamToTarget(pmsBrandParam, source);
        if (flag && !commonMethod.gainConditionFromObjectByField(source).isEmpty()){
            brandRepository.save(source);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateBrand(Integer id, PmsBrandParam pmsBrandParam) {
        if (brandRepository.findById(id).isPresent()) {
            PmsBrand source = brandRepository.findById(id).get();
            Boolean flag = commonMethod.setParamToTarget(pmsBrandParam, source);
            if (flag){
                brandRepository.save(source);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteBrand(Integer id) {
        if (brandRepository.findById(id).isPresent()){
            brandRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteBrand(List<Integer> ids) throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();
        ids.forEach(id->{
            if(brandRepository.findById(id).isPresent()){
                brandRepository.deleteById(id);
                atomicInteger.getAndIncrement();
            }
        });
        if (atomicInteger.get()== ids.size()){
            return true;
        }
        throw new Exception("failure to delete, Transaction roll back");
    }

    @Override
    public List<PmsBrand> listBrand(String keyword, int pageNum, int pageSize) {
        if (keyword!=null){
            return brandRepository.findAllByName(keyword);
        }
        return null;
    }

    @Override
    public PmsBrand getBrand(Integer id) throws Exception {
        if (brandRepository.findById(id).isPresent()){
            return brandRepository.findById(id).get();
        }
        throw new Exception("can find brand id("+id+")in database");
    }

    @Override
    public Boolean updateShowStatus(List<Integer> ids, Integer showStatus) {
        return updateShowOrFactoryStatus(ids,showStatus,SHOW_METHOD);
    }

    @Override
    public Boolean updateFactoryStatus(List<Integer> ids, Integer factoryStatus) {
        return updateShowOrFactoryStatus(ids,factoryStatus,FACTORY_METHOD);
    }


    private Boolean updateShowOrFactoryStatus(List<Integer> ids,Integer status,String method){
        AtomicInteger num = new AtomicInteger();
        ids.forEach(id->{
            if (brandRepository.findById(id).isPresent()) {
                PmsBrand brand = brandRepository.findById(id).get();
                if (method.equals(SHOW_METHOD)){
                    brand.setShowStatus(status);
                }else if (method.equals(FACTORY_METHOD)){
                    brand.setFactoryStatus(status);
                }
                try {
                    brandRepository.save(brand);
                    num.incrementAndGet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return num.get()==ids.size();
    }
}
