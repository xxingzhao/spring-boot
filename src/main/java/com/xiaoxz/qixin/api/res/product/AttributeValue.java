package com.xiaoxz.qixin.api.res.product;

import java.util.List;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/31
 * @Modified by :
 **/
public class AttributeValue {
    private String value;
    private String controlValue;
    private Byte conditions;
    private String regex;
    private String remind;
    private Integer attributeType;
    private Byte unit;
    private List<AttributeRestrict> attributeRestricts;

    public AttributeValue() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getControlValue() {
        return controlValue;
    }

    public void setControlValue(String controlValue) {
        this.controlValue = controlValue;
    }

    public Byte getConditions() {
        return conditions;
    }

    public void setConditions(Byte conditions) {
        this.conditions = conditions;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public Integer getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(Integer attributeType) {
        this.attributeType = attributeType;
    }

    public Byte getUnit() {
        return unit;
    }

    public void setUnit(Byte unit) {
        this.unit = unit;
    }

    public List<AttributeRestrict> getAttributeRestricts() {
        return attributeRestricts;
    }

    public void setAttributeRestricts(List<AttributeRestrict> attributeRestricts) {
        this.attributeRestricts = attributeRestricts;
    }
}
