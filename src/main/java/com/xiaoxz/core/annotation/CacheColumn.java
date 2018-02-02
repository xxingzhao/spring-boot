package com.xiaoxz.core.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/2/2
 * @Modified by :
 **/
public class CacheColumn {

    private String fieldName;
    private String fieldColumn;
    private Field field;
    private Method getter;
    private Method setter;

    public CacheColumn() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldColumn() {
        return fieldColumn;
    }

    public void setFieldColumn(String fieldColumn) {
        this.fieldColumn = fieldColumn;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Method getGetter() {
        return getter;
    }

    public void setGetter(Method getter) {
        this.getter = getter;
    }

    public Method getSetter() {
        return setter;
    }

    public void setSetter(Method setter) {
        this.setter = setter;
    }
}
