package com.xiaoxz.core.annotation;


import com.xiaoxz.anno.GeneratorType;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/2/2
 * @Modified by :
 **/
public class SqlUtil {

    private String insertSql = "insert into %s (%s) values (%s)";
    private String updateSql = "update %s set %s where %s";
    private String selectSql = "select * from %s";
    private String getSelectSql = "select *from %s where %s";
    private String deleteSql = "delete from %s where %s";
    private String selectCountSql = "select count(*) from %s where %s";
    private String selectExtendSql = "select * from %s where %s";

    private SqlUtil(){}

    private static class SingletonHolder {
        public static SqlUtil instance = new SqlUtil();
    }

    public static SqlUtil getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 生成默认插入SQL语句
     * @param cacheEntity
     * @param obj
     */
    public void generateInsertSQL(CacheEntity cacheEntity, Object obj)  {
        Map<String, CacheColumn> columnMap = cacheEntity.getColumnMap();
        List<String> insertKeys   = new ArrayList<>();
        List<String> insertValues = new ArrayList<>();
        try {
            for(Map.Entry<String, CacheColumn> entry : columnMap.entrySet()) {
                CacheColumn cacheColumn = entry.getValue();
                Method getter = cacheColumn.getGetter();
                Object value = getter.invoke(obj);
                if(!"".equals(value) && value != null) {
                    insertKeys.add(cacheColumn.getFieldColumn());
                    insertValues.add(":"+cacheColumn.getFieldName());
                }
            }
            for(Map.Entry<String, CacheId> entry : cacheEntity.getCacheId().entrySet()) {
                CacheId cacheId = entry.getValue();
                if(cacheId.getGeneratorType() != GeneratorType.INCREMENT) {
                    insertKeys.add(cacheId.getColumn());
                    insertValues.add(":"+cacheId.getName());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String insertSQL = String.format(insertSql, cacheEntity.getTableName(),
                StringUtils.join(insertKeys, ","),
                StringUtils.join(insertValues, ","));
        cacheEntity.setInsertSQL(insertSQL);
    }

    /**
     * 根据主键ID生成SQL更新语句
     * @param cacheEntity
     * @param obj
     */
    public void generateUpdateSql(CacheEntity cacheEntity, Object obj) {
        Map<String, CacheColumn> columnMap = cacheEntity.getColumnMap();
        Map<String, CacheId> cacheIdMap    = cacheEntity.getCacheId();
        List<String> updateKeys  = new ArrayList<>();
        List<String> cacheIdKeys = new ArrayList<>();
        try {
            for(Map.Entry<String, CacheColumn> entry : columnMap.entrySet()) {
                CacheColumn cacheColumn = entry.getValue();
                Method getter = cacheColumn.getGetter();
                Object value = getter.invoke(obj);
                if(!"".equals(value) && value != null) {
                    updateKeys.add(cacheColumn.getFieldColumn() + "=:" + cacheColumn.getFieldName());
                }
            }
            for(Map.Entry<String, CacheId> entry : cacheIdMap.entrySet()) {
                CacheId cacheId = entry.getValue();
                cacheIdKeys.add(cacheId.getColumn() +"=:" + cacheId.getName());
            }
            String updateSQL = String.format(updateSql, cacheEntity.getTableName(),
                                            StringUtils.join(updateKeys, ","),
                                            StringUtils.join(cacheIdKeys, ","));
            cacheEntity.setUpdateSQL(updateSQL);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据主键生成删除语句
     * @param cacheEntity
     */
    public void generateDeleteSql(CacheEntity cacheEntity) {
        Map<String, CacheId> cacheIdMap = cacheEntity.getCacheId();
        List<String> idKeys = new ArrayList<>();
        for(Map.Entry<String, CacheId> entry : cacheIdMap.entrySet()) {
            CacheId cacheId = entry.getValue();
            idKeys.add(cacheId.getColumn() +"=:" + cacheId.getName());
        }
        String deleteSQL = String.format(deleteSql, cacheEntity.getTableName(), StringUtils.join(idKeys, ","));
        cacheEntity.setDeleteSQL(deleteSQL);
    }

    /**
     * 生成全表查询SQL语句
     * @param cacheEntity
     */
    public void generateSelect(CacheEntity cacheEntity) {
        String selectSQL = String.format(selectSql,cacheEntity.getTableName());
        cacheEntity.setSelectSQL(selectSQL);
    }

    /**
     * 主键ID查询
     * @param cacheEntity
     */
    public void geterateSelectById(CacheEntity cacheEntity) {
        Map<String, CacheId> cacheIdMap = cacheEntity.getCacheId();
        List<String> idKeys = new ArrayList<>();
        for(Map.Entry<String, CacheId> entry : cacheIdMap.entrySet()) {
            CacheId cacheId = entry.getValue();
            idKeys.add(cacheId.getColumn() +"=:" + cacheId.getName());
        }
        String slectSQL = String.format(getSelectSql, cacheEntity.getTableName(), StringUtils.join(idKeys, ","));
        cacheEntity.setGetListSQL(slectSQL);
    }


}
