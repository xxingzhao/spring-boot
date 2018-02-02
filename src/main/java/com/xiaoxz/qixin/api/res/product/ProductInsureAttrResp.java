package com.xiaoxz.qixin.api.res.product;

import com.xiaoxz.qixin.api.res.BaseResp;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/31
 * @Modified by :
 **/
public class ProductInsureAttrResp extends BaseResp{

    public ProductInsureAttrResp() {
    }

    private String caseCode;
    private InsureAttribute insureAttribute;


    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public InsureAttribute getInsureAttribute() {
        return insureAttribute;
    }

    public void setInsureAttribute(InsureAttribute insureAttribute) {
        this.insureAttribute = insureAttribute;
    }
}
