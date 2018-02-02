package com.xiao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaoxz.qixin.api.client.OpenApiOperation;
import com.xiaoxz.qixin.api.common.ProxyFactory;
import com.xiaoxz.qixin.api.req.product.ProductInsureAttrReq;
import com.xiaoxz.qixin.api.req.product.ProductListReq;
import com.xiaoxz.qixin.api.res.CommonResult;
import com.xiaoxz.qixin.api.res.product.ProductInsureAttrResp;
import com.xiaoxz.qixin.api.res.product.ProductListResp;

import java.util.UUID;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/31
 * @Modified by :
 **/
public class QXAPITest {


   private OpenApiOperation operation = ProxyFactory.create(OpenApiOperation.class);
   ObjectMapper objectMapper = new ObjectMapper();

   public static void main(String[] args){
       try {
           new QXAPITest().testProductInsureAttr();
       } catch (JsonProcessingException e) {
           e.printStackTrace();
       }
   }

   public void testProductList() throws JsonProcessingException {
       ProductListReq productListReq = new ProductListReq();
       productListReq.setTransNo(UUID.randomUUID().toString());
       productListReq.setPartnerId(1018364);
       CommonResult<ProductListResp> respCommonResult = operation.productList(productListReq);
       System.out.println(objectMapper.writeValueAsString(respCommonResult));
   }

   public void testProductInsureAttr() throws JsonProcessingException {
       ProductInsureAttrReq req = new ProductInsureAttrReq();
       req.setTransNo(UUID.randomUUID().toString());
       req.setPartnerId(1018364);
       req.setCaseCode("QX000000002525");
       CommonResult<ProductInsureAttrResp> result = operation.productInsuredAttr(req);
       System.out.println(objectMapper.writeValueAsString(result));
   }

   public void decrypt(String data, String key) {

   }

}
