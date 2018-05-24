package com.sbg.automation.vending.service;

import com.sbg.automation.vending.dto.VendingBasket;
import com.sbg.automation.vending.jpa.entity.Product;



public interface Vending {

    VendingBasket updateBasket(Product product, Long OrderId);

    Double makePayment(Double amountPaid, VendingBasket basket);
}
