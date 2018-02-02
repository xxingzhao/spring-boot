package com.xiaoxz.qixin.api.res;

public class CommonResult<T> {
    private int respCode;
    private String respMsg;
    private T data;

    public CommonResult() {
    }

    public int getRespCode() {
        return this.respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return this.respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
