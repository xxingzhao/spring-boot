package com.xiaoxz.core.annotation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/2/2
 * @Modified by :
 **/
public class CacheEntity {

    //类的表名
    private String tableName;
    /**
     * 持久化类类型
     */
    private Class clazz;
    /**
     * 默认插入语句
     */
    private String insertSQL;
    /**
     * 默认查询语句
     */
    private String selectSQL;
    /**
     * 多添件查询
     */
    private String slectConditionsSQL;
    /**
     * 默认更新语句
     */
    private String updateSQL;
    /**
     * 默认删除语句
     */
    private String deleteSQL;
    /**
     * 默认主键ID查询语句
     */
    private String getListSQL;
    /**
     * 默认主键删除语句
     */
    private String deleteListSQL;
    /**
     * 主键
     */
    private Map<String, CacheId> idMap = new HashMap<>();
    /**
     * 字段
     */
    private Map<String, CacheColumn> columnMap = new HashMap<>();


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getInsertSQL() {
        return insertSQL;
    }

    public void setInsertSQL(String insertSQL) {
        this.insertSQL = insertSQL;
    }

    public String getSelectSQL() {
        return selectSQL;
    }

    public void setSelectSQL(String selectSQL) {
        this.selectSQL = selectSQL;
    }

    public String getUpdateSQL() {
        return updateSQL;
    }

    public void setUpdateSQL(String updateSQL) {
        this.updateSQL = updateSQL;
    }

    public String getDeleteSQL() {
        return deleteSQL;
    }

    public void setDeleteSQL(String deleteSQL) {
        this.deleteSQL = deleteSQL;
    }

    public String getGetListSQL() {
        return getListSQL;
    }

    public void setGetListSQL(String getListSQL) {
        this.getListSQL = getListSQL;
    }

    public String getDeleteListSQL() {
        return deleteListSQL;
    }

    public void setDeleteListSQL(String deleteListSQL) {
        this.deleteListSQL = deleteListSQL;
    }

    public Map<String, CacheColumn> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(Map<String, CacheColumn> columnMap) {
        this.columnMap = columnMap;
    }

    public void putCacheId(CacheId id) {
        idMap.put(id.getName(), id);
    }

    public Map<String, CacheId> getCacheId() {
        return idMap;
    }

    public String getSlectConditionsSQL() {
        return slectConditionsSQL;
    }

    public void setSlectConditionsSQL(String slectConditionsSQL) {
        this.slectConditionsSQL = slectConditionsSQL;
    }
}
