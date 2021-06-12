package com.axiaobug.service.impl;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.pojo.sms.SmsHomeBrand;
import com.axiaobug.repository.sms.SmsHomeBrandRepository;
import com.axiaobug.service.SmsHomeBrandService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * management new product Service impl
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class SmsHomeBrandServiceImpl implements SmsHomeBrandService {

    @Resource
    private CommonMethod commonMethod;

    @Resource
    private SmsHomeBrandRepository brandRepository;

    @Override
    public Boolean create(List<SmsHomeBrand> homeBrandList) throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();
        if (!homeBrandList.isEmpty()){
            homeBrandList.forEach(homeBrand->{
                if (!commonMethod.isEmptyObject(homeBrand)){
                    try {
                        brandRepository.save(homeBrand);
                        atomicInteger.incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            if (atomicInteger.get() == homeBrandList.size()){
                return true;
            }else {
                throw new Exception("有一个实体字段为全空,请重新检查后提交,操作以回滚");
            }
        }
        throw  new Exception("提交的内容为空,请检查后重新提交");
    }

    @Override
    public Boolean updateSort(Integer id, Integer sort) throws Exception {
        if (brandRepository.findById(id).isPresent() && sort!= null){
            SmsHomeBrand homeBrand = brandRepository.findById(id).get();
            homeBrand.setSort(sort);
            try {
                brandRepository.save(homeBrand);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("数据库中查询不到Id: "+id+"或者排名参数是空");
    }

    @Override
    public Boolean delete(List<Integer> ids) throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();
        if (!ids.isEmpty()){
            ids.forEach(id->{
                if (brandRepository.existsById(id)){
                    try {
                        brandRepository.deleteById(id);
                        atomicInteger.incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return atomicInteger.get()==ids.size();
        }
        throw new Exception("批量删除发生异常(空ids),操作已回滚,请检查后重试.");
    }

    @Override
    public Boolean updateRecommendStatus(List<Integer> ids, Integer recommendStatus) throws Exception {
        if (!ids.isEmpty() && recommendStatus!=null){
            AtomicInteger atomicInteger = new AtomicInteger();
            ids.forEach(id->{
                if (brandRepository.findById(id).isPresent()){
                    SmsHomeBrand homeBrand = brandRepository.findById(id).get();
                    homeBrand.setRecommendStatus(recommendStatus);
                    try {
                        brandRepository.save(homeBrand);
                        atomicInteger.incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return atomicInteger.get()==ids.size();
        }
        throw new Exception("批量修改推荐品牌状态发生异常(空ids或者状态为空),操作已回滚,请检查后重试.");
    }

    @Override
    public List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        if (brandName==null && recommendStatus == null){
            return brandRepository.findAll(pageable).getContent();
        }else {
            SmsHomeBrand homeBrand = new SmsHomeBrand();
            if (brandName!=null){
                homeBrand.setBrandName(brandName);
            }
            if (recommendStatus!=null){
                homeBrand.setRecommendStatus(recommendStatus);
            }
            Example<SmsHomeBrand> example = Example.of(homeBrand);
            return brandRepository.findAll(example,pageable).getContent();
        }

    }
}
