package com.sbg.automation.vending.service;

import com.sbg.automation.vending.dto.VendingBasket;
import com.sbg.automation.vending.jpa.entity.Product;



public class VendingImpl implements Vending{

    @Override
    public VendingBasket updateBasket(Product product, Long OrderId) {
        return null;
    }

    @Override
    public Double makePayment(Double amountPaid, VendingBasket basket) {
        return null;
    }
}
