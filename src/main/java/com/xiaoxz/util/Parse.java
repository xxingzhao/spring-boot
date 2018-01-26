package com.xiaoxz.util;

import com.xiaoxz.anno.Id;
import com.xiaoxz.dict.SqlOperateEnum;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/22
 * @Modified by :
 **/
public class Parse {

    private static  String insertTPL = "insert into %s (%s) value(%s)";
    private static  String updateTPL = "update %s set %s where %s";
    private static  String deleteTPL = "delete from %s where %s";
    private static  String getTPL    = "select * from %s where %s";
    /**
     * 获取参数类型
     * @param obj
     * @return
     */
    public static Integer[] getArgsType(Object obj, SqlOperateEnum sqlOperateEnum) {
        Map<String, Method> getterMap = ReflectionUtil.getters(obj.getClass());
        List<Integer> list = new LinkedList<>();
        try {
            for(Map.Entry<String, Method> entry : getterMap.entrySet()) {
                Method getter = entry.getValue();
                Object value = getter.invoke(obj);
                Field field = obj.getClass().getDeclaredField(entry.getKey());
                Id id = field.getAnnotation(Id.class);
                if(id != null && !SqlOperateEnum.SQL_UPDATE.equals(sqlOperateEnum)) {
                    continue;
                }
                if(value != null) {
                    if(Modifier.isPrivate(field.getModifiers())) {
                        field.setAccessible(true);
                    }
                    if("java.lang.String".equals(field.get(obj).getClass().getName())) {
                        list.add(Types.VARCHAR);
                    } else if("java.lang.Double".equals(field.get(obj).getClass().getName())) {
                        list.add(Types.DECIMAL);
                    } else if("java.lang.Integer".equals(field.get(obj).getClass().getName())) {
                        list.add(Types.INTEGER);
                    } else if("java.util.Date".equals(field.get(obj).getClass().getName())) {
                        list.add(Types.DATE);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        Integer[] typs = new Integer [list.size()];
        return list.toArray(typs);
    }

    /**
     * 获取参数
     * @param obj
     * @return
     */
    public static Object[] getArgs(Object obj, SqlOperateEnum sqlOperateEnum) {
        Map<String, Method> getterMap = ReflectionUtil.getters(obj.getClass());
        List<Object> list = new LinkedList<>();
        try {
            for(Map.Entry<String, Method> entry : getterMap.entrySet()) {
                Method getter = entry.getValue();
                Field field = obj.getClass().getDeclaredField(entry.getKey());
                Id id = field.getAnnotation(Id.class);
                if(id != null) {
                    continue;
                }

                if(getter != null) {
                    Object value = getter.invoke(obj);
                    if(value != null) {
                        list.add(value);
                    }
                }
            }

            if(SqlOperateEnum.SQL_UPDATE.getName().equals(sqlOperateEnum.getName())) {
                Field fieldId = obj.getClass().getDeclaredField("id");
                if(fieldId != null) {
                    fieldId.setAccessible(true);
                    Object value = fieldId.get(obj);
                    list.add(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.toArray();
    }

    /**
     * 创建 insert sql 语句
     * @param obj
     * @return
     */
    public static String createInsert(Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ");
        String tableName = ReflectionUtil.getTableName(obj.getClass());
        sb.append(tableName + "(");
        Map<String, Method> getterMap = ReflectionUtil.getters(obj.getClass());
        int counts = 0;
        try {
            for(Map.Entry<String, Method> entry : getterMap.entrySet()) {
                Method getter = entry.getValue();
                String fieldName = entry.getKey();
                Object value = getter.invoke(obj);
                if(value != null) {
                    sb.append(ReflectionUtil.fieldToCloumn(fieldName)).append(",");
                    counts++;
                }
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append(")values(");
            for(int i=0; i < counts; i++) {
                sb.append("?,");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append(")");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 创建更新sql
     * @param obj
     * @return
     */
    public static String createUpdate(Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("update " + ReflectionUtil.getTableName(obj.getClass()) +" set ");
        Map<String, Method> getterMap = ReflectionUtil.getters(obj.getClass());
        List<String> linkedList = new LinkedList<>();
        try {
            for(Map.Entry<String, Method> entry : getterMap.entrySet()) {
                Method method = entry.getValue();
                if(method != null) {
                    Object value = method.invoke(obj);
                    String fieldName = entry.getKey();
                    Id id = obj.getClass().getDeclaredField(fieldName).getAnnotation(Id.class);
                    if(id != null) {
                        continue;
                    }
                    if(value != null) {
                        linkedList.add(ReflectionUtil.fieldToCloumn(fieldName) + "=?");
                    }
                }
            }
            sb.append(String.join(",", linkedList));
            sb.append(" where id=?");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 创建查询语句
     * @param clazz
     * @return
     */
    public static String createSelect(Class clazz) {
        StringBuilder sql = new StringBuilder("select ");
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            String cloumn = ReflectionUtil.fieldToCloumn(field.getName());
            sql.append(cloumn + " as " + field.getName() +",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" from " + ReflectionUtil.getTableName(clazz));
        return sql.toString();
    }



    /**
     * 获取主键ID
     * @param obj
     * @return
     */
    public static Object getId(Object obj) {
        Object value = null;
        try {
            Field field = obj.getClass().getDeclaredField("id");
            Method getter = ReflectionUtil.getter(obj.getClass(), field);
            value = getter.invoke(obj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }
}
