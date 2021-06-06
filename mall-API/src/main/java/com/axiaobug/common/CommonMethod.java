package com.axiaobug.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import org.springframework.stereotype.Component;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
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


    public Boolean setParamToTarget(Object target,Object source){
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
        //取出所有属性
        if (ObjectUtil.isNotNull(obj)){
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field f : fields) {
                String fieldName = f.getName();
                //取属性值
                Object value = getFieldValue(obj, fieldName);
                if (ObjectUtil.isNotNull(value)) {
                   String k = capitalizeFirstLetter(fieldName);
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

}


