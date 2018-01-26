package com.xiaoxz.util;

import com.xiaoxz.anno.Table;
import com.xiaoxz.bean.User;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/19
 * @Modified by :
 **/
public class ReflectionUtil {

    /**
     * 根据类名称获取表名
     * @param clazz
     * @return
     */
    public static String getTableName(Class clazz) {
        Assert.notNull(clazz, "字节码不能为空");
        try {
            Table table = (Table) clazz.getAnnotation(Table.class);
            return table.name();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * setter method
     * @param clazz
     * @return
     */
    public static List<Method> setterList(Class clazz) {
        List<Method> list = new ArrayList<Method>();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field =  fields[i];
            list.add(setter(clazz, field));
        }
        return list;
    }

    /**
     * getter method
     * @param clazz
     * @return
     */
    public static List<Method> getterList(Class clazz) {
        List<Method> list = new ArrayList<Method>();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field =  fields[i];
            list.add(getter(clazz, field));
        }
        return list;
    }
    /**
     * 获取get方法映射  key fielName value method
     * @param clazz
     * @return
     */
    public static Map<String, Method> getters(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Method> getters = new HashMap<String, Method>();
        for(Field field : fields) {
            Method method = getter(clazz, field);
            getters.put(field.getName(), method);
        }
        return getters;
    }

    /**
     * 获取set方法映射  key fielName value method
     * @param clazz
     * @return
     */
    public static Map<String, Method> setters(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Method> setters = new HashMap<String, Method>();
        for(Field field : fields) {
            Method method = setter(clazz, field);
            setters.put(field.getName(), method);
        }
        return setters;
    }
    /**
     * 获取get 方法
     * @param clazz
     * @param field
     * @return
     */
    public static Method getter(Class clazz, Field field) {
        Assert.notNull(field, "字段名不能为空!");
        String prefix = field.getName().substring(0, 1).toUpperCase();
        String name   = prefix + field.getName().substring(1);
        if(field.getType() == Boolean.class || field.getType() == boolean.class) {
            name = "is" + name;
        } else {
            name = "get" + name;
        }
        try {
            Method method = clazz.getMethod(name);
            return method;
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 获取set 方法
     * @param clazz
     * @param field
     * @return
     */
    public static Method setter(Class clazz, Field field) {
        Assert.notNull(field, "字段不能为空");
        String prefix = field.getName().substring(0, 1).toUpperCase();
        String name   = prefix + field.getName().substring(1);
        try{
            name = "set" + name;
            Method method = clazz.getMethod(name);
            return method;
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 字段转化为表字段  userName  user_name
     * @param fieldName
     * @return
     */
    public static String fieldToCloumn(String fieldName) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < fieldName.length(); i++) {
            char c = fieldName.charAt(i);
            if(Character.isUpperCase(c)) {
                sb.append('_');
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) throws Exception{
        Map<String, Method> getters = getters(User.class);
        User u = new User();
        u.setId(10);
        u.setUserName("xiaoxz");
        u.setNickName("xxz");
        u.setPhone("123456");
        u.setPassWord("aaaaaa");
        for (Map.Entry<String, Method> getter : getters.entrySet()) {
            System.out.println(getter.getKey() + ":" + getter.getValue().invoke(u));
            System.out.println(fieldToCloumn(getter.getKey()));
        }
    }
}
