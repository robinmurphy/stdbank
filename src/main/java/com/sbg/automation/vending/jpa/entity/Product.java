package com.sbg.automation.vending.jpa.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS", schema = "SBG")
public class Product {

    @Id
    @Column(name = "PRODUCT_KEY")

    private Long productKey;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Column(name = "ITEM_PRICE")
    private Double itemValue;

    @Column(name = "QUANTITY")
    private int quantity;

    public Product(Long productKey, String description, Double itemValue, int quantity) {
        this.productKey = productKey;
        this.description = description;
        this.itemValue = itemValue;
        this.quantity = quantity;
    }

    public Product() {
    }

    public Long getProductKey() {
        return productKey;
    }

    public void setProductKey(Long productKey) {
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
