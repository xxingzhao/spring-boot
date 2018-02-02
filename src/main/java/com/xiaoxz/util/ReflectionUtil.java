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
        Method[] setters = clazz.getDeclaredMethods();
        for(Method method : setters) {
            String name = method.getName();
            if(name.startsWith("set") && method.getParameterTypes().length == 1) {
                list.add(method);
            }
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
        Method[] methods = clazz.getDeclaredMethods();
        for(Method getter : methods) {
            String name = getter.getName();
            if(name.startsWith("get") && getter.getParameterTypes().length == 0) {
                list.add(getter);
            }
        }
        return list;
    }
    /**
     * 获取get方法映射  key fielName value method
     * @param clazz
     * @return
     */
    public static Map<String, Method> getters(Class clazz) {
        Map<String, Method> getterMap = new HashMap<String, Method>();
        List<Method> getterList = getterList(clazz);
        for(Method method : getterList) {
            getterMap.put(field(method), method);
        }
        return getterMap;
    }

    public static String field(Method method) {
        String name = null;
        if(method.getName().startsWith("is")) {
            name = method.getName().substring(2);
        } else {
            name = method.getName().substring(3);
        }
        String fieldName = name.substring(0,1).toLowerCase() + name.substring(1);
        return fieldName;
    }

    /**
     * 获取set方法映射  key fielName value method
     * @param clazz
     * @return
     */
    public static Map<String, Method> setters(Class clazz) {
        Map<String, Method> setterMap = new HashMap<String, Method>();
        List<Method> methodList =  setterList(clazz);
        for(Method method : methodList) {
            setterMap.put(field(method), method);
        }
        return setterMap;
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

    /**
     * 获取字段名-field 集合
     * @param clazz
     * @return
     */
    public static Map<String, Field> fieldToMaps(Class clazz) {
        Map<String, Field> fieldMap = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }

    /**
     * 表字段column - field 集合
     * @param clazz
     * @return
     */
    public static Map<String, Field> fielToCloumnMaps(Class clazz) {
        Map<String, Field> fieldMap = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            fieldMap.put(ReflectionUtil.fieldToCloumn(field.getName()), field);
        }
        return fieldMap;
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
