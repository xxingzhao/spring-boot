package com.xiaoxz.core.annotation;

import com.xiaoxz.anno.GeneratorType;
import com.xiaoxz.anno.IdType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/2/2
 * @Modified by :
 **/
public class CacheId {

    private String name;
    private String column;
    private Field field;
    private Method getter;
    private Method setter;
    private IdType idType;
    private GeneratorType generatorType;

    public CacheId() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
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

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public GeneratorType getGeneratorType() {
        return generatorType;
    }

    public void setGeneratorType(GeneratorType generatorType) {
        this.generatorType = generatorType;
    }
}
