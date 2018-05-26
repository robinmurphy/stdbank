package com.sbg.automation.vending.dto;

import com.sbg.automation.vending.payment.Currency;

import java.util.Map;

public class TakeAwayBasketDto {

    private VendingBasketDto basket;
    private Map<Currency, Double> changeConfiguration;
    private Double change;

    public VendingBasketDto getBasket() {
        return basket;
    }

    public void setBasket(VendingBasketDto basket) {
        this.basket = basket;
    }

    public Map getChangeConfiguration() {
        return changeConfiguration;
    }

    public void setChangeConfiguration(Map changeConfiguration) {
        this.changeConfiguration = changeConfiguration;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }
}
