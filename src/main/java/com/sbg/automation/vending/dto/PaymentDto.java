package com.sbg.automation.vending.dto;

public class PaymentDto {

    private Double amountPaid;
    private Double basketTotal;

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Double getBasketTotal() {
        return basketTotal;
    }

    public void setBasketTotal(Double basketTotal) {
        this.basketTotal = basketTotal;
    }
}
