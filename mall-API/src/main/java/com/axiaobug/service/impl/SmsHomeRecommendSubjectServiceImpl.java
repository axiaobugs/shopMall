package com.axiaobug.service.impl;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.pojo.sms.SmsHomeRecommendSubject;
import com.axiaobug.repository.sms.SmsHomeRecommendSubjectRepository;
import com.axiaobug.service.SmsHomeRecommendSubjectService;
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
public class SmsHomeRecommendSubjectServiceImpl implements SmsHomeRecommendSubjectService {

    @Resource
    private SmsHomeRecommendSubjectRepository subjectRepository;

    @Resource
    private CommonMethod commonMethod;

    @Override
    public Boolean create(List<SmsHomeRecommendSubject> recommendSubjectList) throws Exception {
        return commonMethod.createWithList(recommendSubjectList,subjectRepository);
    }

    @Override
    public Boolean updateSort(Integer id, Integer sort) {
        if (id==null || sort==null ){
            return false;
        }
        if (subjectRepository.findById(id).isPresent()){
            SmsHomeRecommendSubject recommendProduct = subjectRepository.findById(id).get();
            recommendProduct.setSort(sort);
            try {
                subjectRepository.save(recommendProduct);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Boolean delete(List<Integer> ids) throws Exception {
        return commonMethod.deleteWithList(ids,subjectRepository);
    }

    @Override
    public Boolean updateRecommendStatus(List<Integer> ids, Integer recommendStatus) throws NoSuchMethodException {
        return commonMethod.updateStatusWithList(ids,recommendStatus,subjectRepository);
    }

    @Override
    public List<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        if (subjectName==null && recommendStatus == null){
            return subjectRepository.findAll(pageable).getContent();
        }else {
            Specification<SmsHomeRecommendSubject> specification = commonMethod.createSpecification(subjectName,recommendStatus,"subjectName");
            return subjectRepository.findAll(specification,pageable).getContent();
        }
    }
}
