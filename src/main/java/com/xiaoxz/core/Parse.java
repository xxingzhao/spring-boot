package com.xiaoxz.core;

import com.alibaba.fastjson.JSON;
import com.xiaoxz.anno.GeneratorType;
import com.xiaoxz.anno.Id;
import com.xiaoxz.anno.IdType;
import com.xiaoxz.anno.Table;
import com.xiaoxz.bean.Staff;
import com.xiaoxz.core.annotation.CacheColumn;
import com.xiaoxz.core.annotation.CacheEntity;
import com.xiaoxz.core.annotation.CacheId;
import com.xiaoxz.core.annotation.SqlUtil;
import com.xiaoxz.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/2/2
 * @Modified by :
 **/
public class Parse {

    private Parse() {}

    private static class SingletonHolder{
        public static Parse instance = new Parse();
    }

    public static Parse getInstance() {
        return SingletonHolder.instance;
    }


    /**
     * 解析持久化类信息
     * @param obj
     * @return
     */
    public CacheEntity parse(Object obj) {
        CacheEntity entity = new CacheEntity();
        Table table = obj.getClass().getAnnotation(Table.class);
        if(table == null) {
            throw new IllegalArgumentException(obj.getClass() + "缺少@table注解");
        }
        entity.setTableName(table.name());
        entity.setClazz(obj.getClass());
        parseField(entity,obj);

        SqlUtil sqlUtil = SqlUtil.getInstance();
        sqlUtil.generateInsertSQL(entity, obj);
        sqlUtil.generateUpdateSql(entity, obj);
        sqlUtil.generateDeleteSql(entity);
        sqlUtil.generateSelect(entity);
        sqlUtil.geterateSelectById(entity);
        return entity;
    }

    /**
    *  @descp:解析持久化类字段
    *  @Author:xiaoxz
    *  @date:2018/2/2
     * @params:No such property: code for class: Script1
    */
    private void parseField(CacheEntity entity, Object obj) {
        Map<String, Field> fieldMap        = new HashMap<>();
        Map<String, Method> getterMap      = new HashMap<>();
        Map<String, Method> setterMap      = new HashMap<>();
        Map<String, CacheColumn> columnMap = new HashMap<>();
        initFieldMembers(entity, obj, fieldMap, getterMap, setterMap, columnMap);
    }


    /**
    *  @descp:初始化持久类字段
    *  @Author:xiaoxz
    *  @date:2018/2/2
     * @params:No such property: code for class: Script1
    */
    private void initFieldMembers(CacheEntity cacheEntity, Object obj,
                                  Map<String, Field> fieldMap,
                                  Map<String, Method> getterMap,
                                  Map<String, Method> setterMap,
                                  Map<String, CacheColumn> columnMap) {

        getterMap = ReflectionUtil.getters(obj.getClass());
        setterMap = ReflectionUtil.setters(obj.getClass());
        fieldMap  = ReflectionUtil.fieldToMaps(obj.getClass());

        for(Map.Entry<String, Field> entry : fieldMap.entrySet()) {
            CacheColumn cacheColumn = new CacheColumn();
            String fieldName = entry.getKey();
            String column = ReflectionUtil.fieldToCloumn(fieldName);
            Field field      = entry.getValue();
            Id id = field.getAnnotation(Id.class);
            if(id != null) {
                initCacheIds(cacheEntity, field, id, getterMap, setterMap);
                continue;
            }
            cacheColumn.setFieldName(fieldName);
            cacheColumn.setField(field);
            cacheColumn.setFieldColumn(column);
            cacheColumn.setGetter(getterMap.get(fieldName));
            cacheColumn.setSetter(setterMap.get(fieldName));
            columnMap.put(fieldName, cacheColumn);
            cacheEntity.setColumnMap(columnMap);
        }
    }

    /**
     * 初始化持久化类主键信息
     * @param cacheEntity
     * @param field
     * @param id
     * @param getterMap
     * @param setterMap
     */
    private void initCacheIds(CacheEntity cacheEntity,
                              Field field,
                              Id id,
                              Map<String, Method> getterMap,
                              Map<String, Method> setterMap) {
        String fieldName = field.getName();
        String column    = ReflectionUtil.fieldToCloumn(fieldName);
        CacheId cacheId = new CacheId();
        cacheId.setField(field);
        cacheId.setName(fieldName);
        cacheId.setColumn(column);
        cacheId.setGetter(getterMap.get(fieldName));
        cacheId.setSetter(setterMap.get(fieldName));
        cacheId.setIdType(field.getType() == String.class ? IdType.STRING : IdType.INT);
        GeneratorType generatorType = id.generator();
        if(GeneratorType.AUTO == generatorType) {
            if(cacheId.getIdType() == IdType.INT) {
                cacheId.setGeneratorType(GeneratorType.INCREMENT);
            } else {
                cacheId.setGeneratorType(GeneratorType.UUID);
            }
        } else {
            cacheId.setGeneratorType(generatorType);
        }
        cacheEntity.putCacheId(cacheId);
    }


    public static void main(String[] args){
        Parse parse = Parse.getInstance();
        Staff staff = new Staff();
        staff.setActiveId(1);
        staff.setAddressId(10);
        staff.setEmail("575503075@qq.com");
        staff.setFirstName("xiao");
        staff.setLastName("xz");
        staff.setLastUpdate(new Date());
        CacheEntity cacheEntity = parse.parse(staff);
        System.out.println(JSON.toJSONString(cacheEntity));
        System.out.println(cacheEntity.getInsertSQL());
        System.out.println(cacheEntity.getUpdateSQL());
        System.out.println(cacheEntity.getDeleteSQL());
        System.out.println(cacheEntity.getSelectSQL());
        System.out.println(cacheEntity.getGetListSQL());
    }


}
