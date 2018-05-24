package com.sbg.automation.vending.jpa.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @Column(name = "PRODUCT_KEY")
    private String productKey;

    @Column(name = "DESCRIPTION", length = 20)
    private String description;

    @Column(name = "ITEM_VALUE")
    private Double itemValue;

    @Column(name = "QUANTITY")
    private int quantity;

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getItemValue() {
        return itemValue;
    }

    public void setItemValue(Double itemValue) {
        this.itemValue = itemValue;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
