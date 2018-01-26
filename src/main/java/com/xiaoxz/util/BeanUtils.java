package com.xiaoxz.util;

import com.alibaba.fastjson.JSON;
import com.xiaoxz.bean.User;
import com.xiaoxz.bean.UserDto;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 将一个javaBean对象的属性值拷贝到另一个对象属性名称、
 * 类型相同的属性上，如果属性名称、类型不一致，将不拷贝
 * @author : xiaoxz
 * @Date: Created in 2018/1/26
 * @Modified by :
 **/
public class BeanUtils {

    private static String GET = "get";
    private static String SET = "set";

    /**
     *
     * @param source
     * @param target
     */
    public static void copy(Object source, Object target) {
        Field[] sourceFields = source.getClass().getDeclaredFields();
        if(sourceFields.length == 0) {
            sourceFields = source.getClass().getSuperclass().getDeclaredFields();
        }
        try {
            for(int i=0, len = sourceFields.length; i<len; i++) {
                Field sourceField = sourceFields[i];
                Field targetField = null;
                try {
                    targetField = target.getClass().getDeclaredField(sourceField.getName());
                    if(targetField.getType() == sourceField.getType()) {
                        String fileName    = sourceField.getName().substring(0, 1).toUpperCase() + sourceField.getName().substring(1);
                        String getMethodName = GET + fileName;
                        String setMethodName = SET + fileName;
                        Method getMethod = source.getClass().getDeclaredMethod(getMethodName, new Class[]{});
                        Method setMethod = target.getClass().getDeclaredMethod(setMethodName, sourceField.getType());
                        Object sourceValue = getMethod.invoke(source);
                        setMethod.invoke(target, sourceValue);
                    } else {
                        continue;
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        User user = new User();
        user.setId(123);
        user.setUserName("xxz");
        user.setPassWord("123456");
        user.setPhone("18702049231");
        user.setNickName("xiaoxz");
        UserDto userDto = new UserDto();
        copy(user, userDto);
        System.out.println(JSON.toJSONString(userDto));
    }
}
