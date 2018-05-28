package com.sbg.automation.vending.dto;

import com.sbg.automation.vending.jpa.entity.Product;

import java.util.List;

public class VendingBasketDto {

    private Long orderId;
    private List<Product> products;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
