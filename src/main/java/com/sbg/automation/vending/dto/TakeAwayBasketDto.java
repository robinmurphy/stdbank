package com.sbg.automation.vending.dto;

import java.util.Map;

public class TakeAwayBasketDto {

    private VendingBasketDto basket;
    private Map<String, Double> changeConfiguration;
    private Double change;
    private Double basketTotal;

    public VendingBasketDto getBasket() {
        return basket;
    }

    public void setBasket(VendingBasketDto basket) {
        this.basket = basket;
    }

    public Map getChangeConfiguration() {
        return changeConfiguration;
    }

    public void setChangeConfiguration(Map<String, Double> changeConfiguration) {
        this.changeConfiguration = changeConfiguration;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Double getBasketTotal() {
        return basketTotal;
    }

    public void setBasketTotal(Double basketTotal) {
        this.basketTotal = basketTotal;
    }
}
