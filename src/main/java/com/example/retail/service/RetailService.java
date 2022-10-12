package com.example.retail.service;

import com.example.retail.request.ProductRequest;

public interface RetailService {

    void addNewProduct(ProductRequest productRequest);

    void addNewQuantityForOldProduct(int productId, int quantity);

    void checkValue();

}
