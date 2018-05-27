package com.sbg.automation.vending.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "VENDING_SALES", schema = "SBG")
public class VendingSales {

    public VendingSales(Date dateOfSale, Double totalValue, int numberOfItems) {
        this.dateOfSale = dateOfSale;
        this.totalValue = totalValue;
        this.numberOfItems = numberOfItems;
    }

    public VendingSales() {
    }

    @Id
    @Column(name = "ORDER_ID")
    @SequenceGenerator(name = "SBG_SALES_SEQ", schema = "SBG", sequenceName = "SBG_SALES_SEQ", initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SBG_SALES_SEQ")
    private Long orderId;

    @Column(name = "DATE_OF_SALE")
    private Date dateOfSale;

    @Column(name = "TOTAL_VALUE")
    private Double totalValue;

    @Column(name = "NUMBER_OF_ITEMS")
    private int numberOfItems;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
}
