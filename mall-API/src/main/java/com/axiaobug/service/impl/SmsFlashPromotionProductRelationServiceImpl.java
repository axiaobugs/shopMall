package com.axiaobug.service.impl;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.dto.SmsFlashPromotionProduct;
import com.axiaobug.pojo.sms.SmsFlashPromotionProductRelation;
import com.axiaobug.repository.sms.SmsFlashPromotionProductRelationRepository;
import com.axiaobug.service.SmsFlashPromotionProductRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {
    @Autowired
    private CommonMethod commonMethod;

    @Autowired
    private SmsFlashPromotionProductRelationRepository relationRepository;

    @Override
    public Boolean create(SmsFlashPromotionProductRelation relation) {
        if (commonMethod.isEmptyObject(relation)){
            return false;
        }
        try {
            relationRepository.save(relation);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Boolean update(Integer id, SmsFlashPromotionProductRelation relation) {
        if (!relationRepository.existsById(id)){
            return false;
        }
        if (commonMethod.isEmptyObject(relation)){
            return false;
        }
        relation.setId(id);
        try {
            relationRepository.save(relation);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        if (relationRepository.existsById(id)){
            try {
                relationRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("删除的Id: "+id+"在数据库中查找不到.请核实后再次提交,已回滚.");
    }

    @Override
    public SmsFlashPromotionProductRelation getItem(Integer id) throws Exception {
        if (relationRepository.findById(id).isPresent()){
            try {
                return relationRepository.findById(id).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new Exception("查找的Id:"+id+"在数据库中查找不到.请核实后再次提交");
    }

    @Override
    public List<SmsFlashPromotionProductRelation> list(Integer flashPromotionId, Integer flashPromotionSessionId, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        SmsFlashPromotionProductRelation relation = new SmsFlashPromotionProductRelation();
        relation.setFlashPromotionId(flashPromotionId);
        relation.setFlashPromotionSessionId(flashPromotionSessionId);
        Example<SmsFlashPromotionProductRelation> example = Example.of(relation);
        return relationRepository.findAll(example, pageable).getContent();

    }

    @Override
    public long getCount(Integer flashPromotionId, Integer flashPromotionSessionId) {

        return 0;
    }
}
