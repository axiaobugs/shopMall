package com.axiaobug.service.impl;

import com.axiaobug.pojo.pms.PmsProductAttributeCategory;
import com.axiaobug.repository.pms.PmsProductAttributeCategoryRepository;
import com.axiaobug.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {

    @Autowired
    private PmsProductAttributeCategoryRepository attributeCategoryRepository;


    @Override
    public Boolean create(String name) {
        PmsProductAttributeCategory attributeCategory = new PmsProductAttributeCategory();
        attributeCategory.setName(name);
        try {
            attributeCategoryRepository.save(attributeCategory);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(Integer id, String name) {
        if (attributeCategoryRepository.findById(id).isPresent()) {
            PmsProductAttributeCategory attributeCategory = attributeCategoryRepository.findById(id).get();
            attributeCategory.setName(name);
            try {
                attributeCategoryRepository.save(attributeCategory);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        if (attributeCategoryRepository.findById(id).isPresent()){
            try {
                attributeCategoryRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public PmsProductAttributeCategory getItem(Integer id) {
        if (attributeCategoryRepository.findById(id).isPresent()) {
            return attributeCategoryRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<PmsProductAttributeCategory> all = attributeCategoryRepository.findAll(pageable);
        return all.getContent();
    }

}
