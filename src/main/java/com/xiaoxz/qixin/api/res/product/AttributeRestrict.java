package com.xiaoxz.qixin.api.res.product;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/31
 * @Modified by :
 **/
public class AttributeRestrict {

    private String name;
    private Byte conditions;
    private String remind;
    private Integer attributeType;
    private String value;
    private Byte unit;
    private String apiName;


    public AttributeRestrict() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getConditions() {
        return conditions;
    }

    public void setConditions(Byte conditions) {
        this.conditions = conditions;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Byte getUnit() {
        return unit;
    }

    public void setUnit(Byte unit) {
        this.unit = unit;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
}
