package com.xiaoxz.qixin.api.res.product;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/31
 * @Modified by :
 **/
public class Product {

    private String prodName;
    private String caseCode;
    private String companyName;
    private String planName;
    private int offShelf;
    private int fristCategory;
    private int secondCategory;

    public Product() {
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getOffShelf() {
        return offShelf;
    }

    public void setOffShelf(int offShelf) {
        this.offShelf = offShelf;
    }

    public int getFristCategory() {
        return fristCategory;
    }

    public void setFristCategory(int fristCategory) {
        this.fristCategory = fristCategory;
    }

    public int getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(int secondCategory) {
        this.secondCategory = secondCategory;
    }
}
