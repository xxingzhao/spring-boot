package com.xiaoxz.qixin.api.res.product;

import com.xiaoxz.qixin.api.res.BaseResp;

import java.util.List;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/31
 * @Modified by :
 **/
public class ProductListResp extends BaseResp {

    private List<Product> products;

    public ProductListResp() {
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
