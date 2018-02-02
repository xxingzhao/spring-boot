package com.xiaoxz.qixin.api.req.product;

import com.xiaoxz.qixin.api.req.BaseReq;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/31
 * @Modified by :
 **/
public class ProductInsureAttrReq extends BaseReq{

    public ProductInsureAttrReq() {
    }

    private String caseCode;

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }
}
