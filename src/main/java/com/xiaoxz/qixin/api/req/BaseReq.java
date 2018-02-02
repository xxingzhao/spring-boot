package com.xiaoxz.qixin.api.req;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/31
 * @Modified by :
 **/
public class BaseReq {

    private String transNo;
    private int partnerId;
    private String ip;
    private int subPartnerId;


    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getSubPartnerId() {
        return subPartnerId;
    }

    public void setSubPartnerId(int subPartnerId) {
        this.subPartnerId = subPartnerId;
    }
}
