package com.axiaobug.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 * TODO: 递归拿到父类的字段
 */
@Component
public class CommonMethod {
    public void timeRangeMatch(List<Predicate> predicates,
                                          Date createTime, Root root,
                                          CriteriaQuery criteriaQuery,
                                          CriteriaBuilder criteriaBuilder,
                                            String filed){
        Date beginOfDay = DateUtil.beginOfDay(createTime);
        Date endOfDay = DateUtil.endOfDay(createTime);
        predicates.add(criteriaBuilder.between(root.get(filed).as(String.class), beginOfDay.toString(), endOfDay.toString()));
    }


    public <T> Boolean createWithList (List<T> list, Object object) throws Exception {
        if (!list.isEmpty()){
            AtomicInteger atomicInteger = new AtomicInteger();
            list.forEach(item->{
                try {
                    Method method = object.getClass().getMethod("save",Object.class);
                    System.out.println("method: "+method);
                    method.invoke(object,item);
                    atomicInteger.incrementAndGet();
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            return atomicInteger.get()==list.size();
        }
        throw new Exception("提交的数组为空,请检查后重试,操作已回滚");
    }

    public Boolean deleteWithList (List<Integer> list, Object object) throws Exception {
        Method existsById = object.getClass().getMethod("existsById", Object.class);
        Method deleteById = object.getClass().getMethod("deleteById", Object.class);
        if (!list.isEmpty()){
            AtomicInteger atomicInteger = new AtomicInteger();
            list.forEach(item->{
                try {
                    if ((Boolean) existsById.invoke(object,item)){
                        deleteById.invoke(object,item);
                        atomicInteger.incrementAndGet();
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            return atomicInteger.get()== list.size();
        }

        throw new Exception("提交的数组为空,请检查后重试,操作已回滚");
    }

    public <T> Boolean updateStatusWithList(List<Integer> list, Integer status,Object object) throws NoSuchMethodException {
        if (list.isEmpty() || status==null){
            return false;
        }else{
            AtomicInteger atomicInteger = new AtomicInteger();
            Method findById = object.getClass().getMethod("findById", Object.class);
            Method save = object.getClass().getMethod("save", Object.class);
            list.forEach(id->{
                try {
                    Optional optional =(Optional) findById.invoke(object, id);
                    if (optional.isPresent()){
                        Object res =  optional.get();
                        Method setRecommendStatus = res.getClass().getMethod("setRecommendStatus", Integer.class);
                        setRecommendStatus.invoke(res,status);
                        try {
                            save.invoke(object,res);
                            atomicInteger.incrementAndGet();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }

            });
            return atomicInteger.get()==list.size();
        }
    }


    public CommonResult<Boolean> response(Boolean flag){
        if(flag){
            return CommonResult.success(true);
        }
        return CommonResult.failed();
    }

    public boolean isEmptyObject(java.io.Serializable obj){
       return gainConditionFromObjectByField(obj).isEmpty();
    }

    public Boolean setParamToTarget(Object target, java.io.Serializable source){
        HashMap<Object, Object> targetMap = gainConditionFromObjectByField(target);
        if (MapUtil.isNotEmpty(targetMap)){
            for (Map.Entry<Object,Object> entry:targetMap.entrySet()) {
                String setMethodName = "set"+entry.getKey().toString();
                ReflectUtil.invoke(source,setMethodName,entry.getValue());
            }
            return MapUtil.isNotEmpty(gainConditionFromObjectByField(source));
        }
        return false;
    }

    /**
    * @Discription:
    * @Param:  obj -> target Object
    * @return: map key:field name  value: value of this field(not null)
    */
    public HashMap<Object, Object> gainConditionFromObjectByField(Object obj) {
        HashMap<Object, Object> res = new HashMap<>(obj.getClass().getDeclaredFields().length);
        if (obj.getClass().getDeclaredFields().length>0){
            List<Field> fieldList = new ArrayList<>() ;
            // get fields from parent class if have
            if (obj.getClass().getSuperclass().getDeclaredFields().length>0) {
                fieldList.addAll(Arrays.asList(obj.getClass().getSuperclass().getDeclaredFields()));
            }
            // get fields from this class
            fieldList.addAll(Arrays.asList(obj.getClass().getDeclaredFields()));
            for (Field f : fieldList) {
                String fieldName = f.getName();
                //取属性值
                Object value = getFieldValue(obj, fieldName);
                String k = capitalizeFirstLetter(fieldName);
                if (ObjectUtil.isNotNull(value)) {
                    res.put(k,value);
                }
            }
        }
        return res;
    }

    private Object getFieldValue(Object owner, String fieldName) {
        Object o = invokeMethod(owner, fieldName);
        if (ObjectUtil.isNotNull(o)) {
            return o;
        }
        return null;
    }

    /**
    * @Discription: get the method from target object
    * @Param: [owner: target object, fieldName : method name]
    * @return:
    */
    private Object invokeMethod(Object owner, String fieldName) {
        Class<?> ownerClass = owner.getClass();


        String methodName = capitalizeFirstLetter(fieldName);

        Method method = null;
        try {
            method = ownerClass.getMethod("get" + methodName);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return "";
        }

        //invoke getMethod
        try {
            assert method != null;
            return method.invoke(owner);
        } catch (Exception e) {
            return "";
        }
    }

    /**
    * @Discription:  capitalize first letter : fieldName -> FieldName
    * @Param:
    * @return:
    */
    private String capitalizeFirstLetter(String name){
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public <T> Specification<T> createSpecification(String name,Integer status){
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name!=null){
                predicates.add(criteriaBuilder.like(root.get("productName").as(String.class),"%"+name+"%"));
            }
            if (status!=null){
                predicates.add(criteriaBuilder.equal(root.get("recommendStatus").as(Integer.class),status));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }


}


