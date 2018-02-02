package com.xiaoxz.qixin.api.res.product;

import java.util.List;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/31
 * @Modified by :
 **/
public class ProductAttribute {

    private String name;
    private String apiName;
    private Byte type;
    private String regex;
    private String defaultRemind;
    private String errorRemind;
    private Byte required;
    private List<AttributeValue> attributeValues;
    private List<AttributeRestrict> attributeRestricts;

    public ProductAttribute() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getDefaultRemind() {
        return defaultRemind;
    }

    public void setDefaultRemind(String defaultRemind) {
        this.defaultRemind = defaultRemind;
    }

    public String getErrorRemind() {
        return errorRemind;
    }

    public void setErrorRemind(String errorRemind) {
        this.errorRemind = errorRemind;
    }

    public Byte getRequired() {
        return required;
    }

    public void setRequired(Byte required) {
        this.required = required;
    }

    public List<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

    public List<AttributeRestrict> getAttributeRestricts() {
        return attributeRestricts;
    }

    public void setAttributeRestricts(List<AttributeRestrict> attributeRestricts) {
        this.attributeRestricts = attributeRestricts;
    }
}
