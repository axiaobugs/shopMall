package com.axiaobug.service.impl;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.dto.PmsProductParam;
import com.axiaobug.dto.PmsProductQueryParam;
import com.axiaobug.dto.PmsProductResult;
import com.axiaobug.pojo.pms.PmsProduct;
import com.axiaobug.pojo.pms.PmsProductVertifyRecord;
import com.axiaobug.repository.pms.PmsProductCategoryRepository;
import com.axiaobug.repository.pms.PmsProductRepository;
import com.axiaobug.repository.pms.PmsProductVertifyRecordRepository;
import com.axiaobug.service.PmsProductService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@Service
public class PmsProductServiceImpl implements PmsProductService {
    private static final String QUERY_NAME = "name";
    private static final String QUERY_SN = "sn";
    private static final String STATUS_VERIFY="verify";
    private static final String STATUS_PUBLISH="publish";
    private static final String STATUS_RECOMMEND="recommend";
    private static final String STATUS_NEW="new";
    private static final String STATUS_DELETE="delete";

    @Resource
    private PmsProductRepository productRepository;

    @Resource
    private PmsProductCategoryRepository categoryRepository;

    @Resource
    private PmsProductVertifyRecordRepository recordRepository;

    @Resource
    private CommonMethod commonMethod;

    @Override
    public int create(PmsProduct product) {
        final long count1 = productRepository.count();
        productRepository.save(product);
        final long count2 = productRepository.count();
        return (int) (count2-count1);
    }

    /*
    TODO: 增加空指针判断.
    */

    @Override
    public PmsProductResult getUpdateInfo(Integer id) {
        if (productRepository.findById(id).isPresent()) {
            PmsProductResult source = new PmsProductResult();
            PmsProduct target = productRepository.findById(id).get();
            commonMethod.setParamToTarget(target, source);
            Integer categoryId = target.getProductCategoryId();
            source.setCateParentId(categoryRepository.findById(categoryId).get().getParentId());
            return source;
        }
        return null;
    }

    @Override
    public int update(Integer id, PmsProductParam productParam) {
        if (productRepository.findById(id).isPresent()) {
            PmsProduct source = productRepository.findById(id).get();
            Boolean flag = commonMethod.setParamToTarget(productParam, source);
            if (flag){
                productRepository.save(source);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        PmsProduct source = new PmsProduct();
        Boolean flag = commonMethod.setParamToTarget(productQueryParam, source);
        if (flag){
            Example<PmsProduct> example = Example.of(source);
            return productRepository.findAll(example,pageable).getContent();
        }
        return null;
    }

    @Override
    public List<PmsProduct> list(String keyword,String field) {
        Specification<PmsProduct> specification = (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = null;
            if (field.equals(QUERY_NAME)){
                predicate = criteriaBuilder.like(root.get("name").as(String.class),"%"+keyword+"%");
            }
            if (field.equals(QUERY_SN)){
                predicate = criteriaBuilder.like(root.get("productSerialNumber").as(String.class),"%"+keyword+"%");
            }
            return criteriaBuilder.and(predicate);
        };
        return productRepository.findAll(specification);
    }

    @Override
    public Boolean updateVerifyStatus(List<Integer> ids, Integer verifyStatus, String detail) throws Exception {
        return  editBatch(ids, verifyStatus, "verify",detail);


    }

    @Override
    public Boolean updatePublishStatus(List<Integer> ids, Integer publishStatus) throws Exception {
        return editBatch(ids,publishStatus,"publish",null);
    }



    @Override
    public Boolean updateRecommendStatus(List<Integer> ids, Integer recommendStatus) throws Exception {
        return editBatch(ids,recommendStatus,"recommend",null);
    }

    @Override
    public Boolean updateDeleteStatus(List<Integer> ids, Integer deleteStatus) throws Exception {
        return editBatch(ids,deleteStatus,"delete",null);
    }

    @Override
    public List<PmsProduct> list(String keyword) {
        return null;
    }

    private Boolean editBatch(List<Integer> ids,Integer status,String field,String detail) throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();
        if (!ids.isEmpty()){
            ids.forEach(id->{
                if (productRepository.findById(id).isPresent()) {
                    PmsProduct product = productRepository.findById(id).get();
                    switch (field){
                        case STATUS_VERIFY:
                            product.setVerifyStatus(status);
                            if (status==1) {
                                PmsProductVertifyRecord vertifyRecord = new PmsProductVertifyRecord();
                                vertifyRecord.setProductId(id);
                                vertifyRecord.setCreateTime(new Date());
                                vertifyRecord.setDetail(detail);
                                vertifyRecord.setVerifyMan("admin");
                                vertifyRecord.setStatus(status);
                                try {
                                    recordRepository.save(vertifyRecord);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        case STATUS_PUBLISH:
                            product.setPublishStatus(status);
                            break;
                        case STATUS_RECOMMEND:
                            product.setRecommendStatus(status);
                            break;
                        case STATUS_DELETE:
                            product.setDeleteStatus(status);
                            break;
                        case STATUS_NEW:
                            product.setNewStatus(status);
                            break;
                        default:
                            break;
                    }
                    try {
                        productRepository.save(product);
                        atomicInteger.incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
            return ids.size() == atomicInteger.get();
        }
        throw new Exception("ids is empty");
    }
}
