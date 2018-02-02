package com.xiaoxz.qixin.api.client;

import com.xiaoxz.qixin.api.req.product.ProductInsureAttrReq;
import com.xiaoxz.qixin.api.req.product.ProductListReq;
import com.xiaoxz.qixin.api.res.CommonResult;
import com.xiaoxz.qixin.api.res.product.ProductInsureAttrResp;
import com.xiaoxz.qixin.api.res.product.ProductListResp;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/31
 * @Modified by :
 **/
public interface OpenApiOperation {

    /**
     * 商品列表
     * @param req
     * @return
     */
    CommonResult<ProductListResp> productList(ProductListReq req);

    /**
     * 根据产品查询产品投保属性信息
     * @param req
     * @return
     */
    CommonResult<ProductInsureAttrResp> productInsuredAttr(ProductInsureAttrReq req);
}
