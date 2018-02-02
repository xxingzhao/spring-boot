package com.xiaoxz.qixin.api.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaoxz.qixin.api.config.Configure;
import com.xiaoxz.qixin.api.res.CommonResult;
import com.xiaoxz.qixin.api.util.HttpUtil;
import com.xiaoxz.qixin.api.util.Md5Utils;

import java.lang.reflect.Type;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/31
 * @Modified by :
 **/
public class RemoteInvoker {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public RemoteInvoker() {
    }


    /**
    *  @descp:远程调用齐欣接口
    *  @Author:xiaoxz
    *  @date:2018/2/1
     * @params:returnType 返回类型 api 调用接口方法 请求对象
    */
    public static <T> CommonResult<T> invoke(Type retutnType, String api, Object object) {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String qxUrl = Configure.Request.baseUrl + api;
        try {
            String requestBody = objectMapper.writeValueAsString(object);
            System.out.println("请求参数：" + requestBody);
            requestBody = new String(requestBody.getBytes("UTF-8"), "UTF-8");
            qxUrl = sing(qxUrl, requestBody);
            String httpResult = HttpUtil.post(qxUrl, requestBody);
            if("error".equals(httpResult)) {
                throw new RuntimeException("调用服务失败");
            } else  {
                CommonResult<T> res = objectMapper.readValue(httpResult, getResponseType(retutnType));
                return res;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
    *  @descp:MD5加密生成sign
    *  @Author:xiaoxz
    *  @date:2018/2/1
     * @params:No such property: code for class: Script1
    */
    private static String sing(String api, String body) {
        if(Configure.Channel.channelKey == null) {
            throw new RuntimeException("请先配置渠道秘钥: " + Configure.Channel.class + "#channelKey");
        } else {
            body = Configure.Channel.channelKey + body;
            String sign = Md5Utils.getUtf8MD5String(body);
            api = api + "?sign=" + sign;
            return api;
        }
    }


    private static JavaType getResponseType(Type returnType) {
        return objectMapper.getTypeFactory().constructType(returnType);
    }
}
