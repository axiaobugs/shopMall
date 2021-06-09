package com.axiaobug.service.impl;

import com.axiaobug.dto.SmsFlashPromotionSessionDetail;
import com.axiaobug.pojo.sms.SmsFlashPromotionSession;
import com.axiaobug.repository.sms.SmsFlashPromotionSessionRepository;
import com.axiaobug.service.SmsFlashPromotionSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class SmsFlashPromotionSessionServiceImpl implements SmsFlashPromotionSessionService {

    @Autowired
    private SmsFlashPromotionSessionRepository sessionRepository;

    @Override
    public Boolean create(SmsFlashPromotionSession promotionSession) {
        try {
            sessionRepository.save(promotionSession);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(Integer id, SmsFlashPromotionSession promotionSession) throws Exception {
        if (sessionRepository.existsById(id)){
            promotionSession.setId(id);
            try {
                sessionRepository.save(promotionSession);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("删除的Id: "+id+"找不到,请检查后重新提交");
    }

    @Override
    public Boolean updateStatus(Integer id, Integer status) throws Exception {
        if (sessionRepository.findById(id).isPresent()){
            SmsFlashPromotionSession session = sessionRepository.findById(id).get();
            session.setStatus(status);
            try {
                sessionRepository.save(session);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("修改的状态对应的Id: "+id+"找不到,请检查后重新提交");
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        if (sessionRepository.existsById(id)){
            try {
                sessionRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        throw new Exception("删除的Id: "+id+"找不到,请检查后重新提交");
    }

    @Override
    public SmsFlashPromotionSession getItem(Integer id) {
        if (sessionRepository.findById(id).isPresent()){
            return sessionRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<SmsFlashPromotionSession> list() {
        return sessionRepository.findAll();
    }

    @Override
    public List<SmsFlashPromotionSessionDetail> selectList(Integer flashPromotionId) {
        return null;
    }
}
