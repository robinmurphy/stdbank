package com.sbg.automation.vending.payment;

import com.sbg.automation.vending.dto.VendingBasket;

import java.util.Map;

public class PaymentProcessor {

    Double calculateTotal(VendingBasket basket){

        return basket.getProducts()
                .stream()
                .filter(o -> o.getItemValue() != null )
                .mapToDouble(o -> o.getItemValue()).sum();
    }

    Double calculateChange(Double basketvalue, Double amountPaid){

        return null;
    }

    Map<Currency, Double> determineChangeConfiguration(Double changeAmount){

        return null;
    }


}
