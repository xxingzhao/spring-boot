package com.xiaoxz.dict;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/22
 * @Modified by :
 **/
public enum SqlOperateEnum {

   SQL_INSERT("insert"), SQL_UPDATE("update"), SQL_DELETE("delete"), SQL_SELECT("select");

   private String name;

   SqlOperateEnum(String name) {
        this.name = name;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
