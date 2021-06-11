package com.axiaobug.service.impl;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.pojo.sms.SmsHomeAdvertise;
import com.axiaobug.repository.sms.SmsHomeAdvertiseRepository;
import com.axiaobug.service.SmsHomeAdvertiseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class SmsHomeAdvertiseServiceImpl implements SmsHomeAdvertiseService {

    @Resource
    private SmsHomeAdvertiseRepository advertiseRepository;

    @Resource
    private CommonMethod commonMethod;

    @Override
    public Boolean create(SmsHomeAdvertise advertise) throws Exception {
        if (!commonMethod.isEmptyObject(advertise)){
            try {
                advertiseRepository.save(advertise);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        throw new Exception("创建广告失败,请检查后重试");
    }

    @Override
    public Boolean delete(List<Integer> ids) throws Exception {
        if (!ids.isEmpty()){
            AtomicInteger atomicInteger = new AtomicInteger();
            ids.forEach(id->{
                if (advertiseRepository.existsById(id)){
                    try {
                        advertiseRepository.deleteById(id);
                        atomicInteger.incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            if (atomicInteger.get()==ids.size()){
                return true;
            }
            throw new Exception("批量删除轮播广告失败,请检查后重试");
        }
        throw new Exception("提供的Id是空,请检查后重试");
    }

    @Override
    public Boolean updateStatus(Integer id, Integer status) throws Exception {
        if (advertiseRepository.findById(id).isPresent()){
            SmsHomeAdvertise advertise = advertiseRepository.findById(id).get();
            advertise.setStatus(status);
            try {
                advertiseRepository.save(advertise);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        throw new Exception("无法查找到需要删除的ID: "+id+"请检查后重试");
    }

    @Override
    public SmsHomeAdvertise getItem(Integer id) throws Exception {
        if (advertiseRepository.findById(id).isPresent()) {
            return advertiseRepository.findById(id).get();
        }
        throw new Exception("无法查找提供的ID: "+id+"请检查后重试");
    }

    @Override
    public Boolean update(Integer id, SmsHomeAdvertise advertise) throws Exception {
        if (advertiseRepository.existsById(id)){
            advertise.setId(id);
            try {
                advertiseRepository.save(advertise);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("无法更新轮播广告,提供的ID: "+id+"不在数据库中,请检查后重试");
    }

    @Override
    public List<SmsHomeAdvertise> list(String name, Integer type, Date endTime, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Specification<SmsHomeAdvertise> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && name.length() > 0) {
                predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
            }
            if (type!=null){
                predicates.add(criteriaBuilder.equal(root.get("type").as(Integer.class),type));
            }
            if (endTime!=null){
                commonMethod.timeRangeMatch(predicates,
                        endTime,
                        root,
                        criteriaQuery,
                        criteriaBuilder,
                        "endTime");
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return advertiseRepository.findAll(specification,pageable).getContent();
    }
}
