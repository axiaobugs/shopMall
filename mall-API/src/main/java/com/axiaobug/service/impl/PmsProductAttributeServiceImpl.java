package com.axiaobug.service.impl;

import com.axiaobug.dto.PmsProductAttributeParam;
import com.axiaobug.dto.ProductAttrInfo;
import com.axiaobug.pojo.pms.PmsProductAttribute;
import com.axiaobug.repository.pms.PmsProductAttributeRepository;
import com.axiaobug.repository.pms.PmsProductCategoryAttributeRelationRepository;
import com.axiaobug.repository.pms.PmsProductCategoryRepository;
import com.axiaobug.service.PmsProductAttributeService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 * TODO: getProductAttrInfo方法尝试连表查询.
 */
@Service
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {

    @Resource
    private PmsProductAttributeRepository attributeRepository;

    @Resource
    private PmsProductCategoryRepository productCategoryRepository;

    @Resource
    private PmsProductCategoryAttributeRelationRepository relationRepository;


    @Override
    public List<PmsProductAttribute> getList(Integer cid, Integer type, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        pmsProductAttribute.setProductAttributeCategoryId(cid);
        pmsProductAttribute.setType(type);
        System.out.println(pmsProductAttribute);
        Example<PmsProductAttribute> example = Example.of(pmsProductAttribute);
        return attributeRepository.findAll(example, pageable).getContent();
    }

    @Override
    public Boolean create(PmsProductAttributeParam inputParam){
        PmsProductAttribute productAttribute = new PmsProductAttribute();
        return createOrUpdateAttribute(productAttribute, inputParam);
    }

    @Override
    public Boolean update(Integer id, PmsProductAttributeParam productAttributeParam) {
        if (attributeRepository.findById(id).isPresent()) {
            PmsProductAttribute productAttribute = attributeRepository.findById(id).get();
            return createOrUpdateAttribute(productAttribute,productAttributeParam);
        }
        return false;
    }

    @Override
    public PmsProductAttribute getItem(Integer id) {
        return attributeRepository.findById(id).isPresent()?attributeRepository.findById(id).get():null;
    }

    @Override
    public Boolean delete(List<Integer> ids) {
        if (!ids.isEmpty()){
            AtomicInteger atomicInteger = new AtomicInteger();
            ids.forEach(id->{
                if (attributeRepository.findById(id).isPresent()){
                    try {
                        attributeRepository.deleteById(id);
                        atomicInteger.getAndIncrement();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return atomicInteger.get() == ids.size();
        }
        return false;
    }

    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Integer productCategoryId) {
        ArrayList<ProductAttrInfo> attrInfos = new ArrayList<>();
        if (productCategoryRepository.findById(productCategoryId).isPresent()) {
            if (!relationRepository.findAllByProductCategoryId(productCategoryId).isEmpty()){
                ProductAttrInfo attrInfo = new ProductAttrInfo();
                relationRepository.findAllByProductCategoryId(productCategoryId).forEach(relation->{
                    attrInfo.setAttributeId(relation.getProductAttributeId());
                    if (attributeRepository.findById(relation.getProductAttributeId()).isPresent()){
                        PmsProductAttribute attribute = attributeRepository.findById(relation.getProductAttributeId()).get();
                        attrInfo.setAttributeCategoryId(attribute.getProductAttributeCategoryId());
                    }else{
                        attrInfo.setAttributeCategoryId(0);
                    }
                    attrInfos.add(attrInfo);

                });
            }
            return attrInfos;
        }
        return null;
    }

    /**
    * common method which could create or update attribute
    * @Param: [productAttribute, inputParam]
    * @return:
    */
    public Boolean createOrUpdateAttribute(PmsProductAttribute productAttribute,
                                           PmsProductAttributeParam inputParam){
        if (inputParam.getProductAttributeCategoryId()!=null){
            productAttribute.setProductAttributeCategoryId(inputParam.getProductAttributeCategoryId());
        }
        if (inputParam.getName()!=null){
            productAttribute.setName(inputParam.getName());
        }
        if (inputParam.getSearchType()!=null){
            productAttribute.setSearchType(inputParam.getSearchType());
        }
        if (inputParam.getInputType()!=null){
            productAttribute.setInputType(inputParam.getInputType());
        }
        if (inputParam.getInputList()!=null&&inputParam.getInputList().length()>0){
            productAttribute.setInputList(inputParam.getInputList());
        }
        if (inputParam.getSort()!=null){
            productAttribute.setSort(inputParam.getSort());
        }
        if (inputParam.getFilterType()!=null){
            productAttribute.setFilterType(inputParam.getFilterType());
        }
        if (inputParam.getSearchType()!=null){
            productAttribute.setSearchType(inputParam.getSearchType());
        }
        if (inputParam.getRelatedStatus()!=null){
            productAttribute.setRelatedStatus(inputParam.getRelatedStatus());
        }
        if (inputParam.getHandAddStatus()!=null){
            productAttribute.setHandAddStatus(inputParam.getHandAddStatus());
        }
        if (inputParam.getType()!=null){
            productAttribute.setType(inputParam.getType());
        }
        try {
            attributeRepository.save(productAttribute);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
