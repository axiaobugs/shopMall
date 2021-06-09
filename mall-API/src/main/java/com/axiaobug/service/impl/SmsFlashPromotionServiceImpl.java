package com.axiaobug.service.impl;

import com.axiaobug.pojo.sms.SmsFlashPromotion;
import com.axiaobug.repository.sms.SmsFlashPromotionRepository;
import com.axiaobug.service.SmsFlashPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class SmsFlashPromotionServiceImpl implements SmsFlashPromotionService {

    @Autowired
    private SmsFlashPromotionRepository promotionRepository;


    @Override
    public Boolean create(SmsFlashPromotion flashPromotion){
        try {
            promotionRepository.save(flashPromotion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(Integer id, SmsFlashPromotion flashPromotion) throws Exception {
        if (promotionRepository.existsById(id)){
            flashPromotion.setId(id);
            try {
                promotionRepository.save(flashPromotion);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("修改的Id: "+id + "不存在,请检查后再试!");
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        if (promotionRepository.existsById(id)){
            try {
                promotionRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("删除的Id: "+id + "不存在,请检查后再试!");
    }

    @Override
    public Boolean updateStatus(Integer id, Integer status) throws Exception {
        if (promotionRepository.findById(id).isPresent()){
            SmsFlashPromotion flashPromotion = promotionRepository.findById(id).get();
            flashPromotion.setStatus(status);
            try {
                promotionRepository.save(flashPromotion);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("修改的Id: "+id + "不存在,请检查后再试!");
    }

    @Override
    public SmsFlashPromotion getItem(Integer id) {
        if (promotionRepository.findById(id).isPresent()){
            return promotionRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum) {
        Specification<SmsFlashPromotion> specification =((root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.like(root.get("title").as(String.class),"%"+keyword+"%");
            return  criteriaBuilder.and(predicate);
        });
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<SmsFlashPromotion> content = promotionRepository.findAll(specification, pageable).getContent();
        if (!content.isEmpty()){
            return content;
        }
        return null;
    }
}
