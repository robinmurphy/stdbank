package com.sbg.automation.vending.service;

import com.sbg.automation.vending.dto.TakeAwayBasketDto;
import com.sbg.automation.vending.dto.VendingBasketDto;
import com.sbg.automation.vending.jpa.entity.Product;

import java.util.List;

public interface Vending {

    TakeAwayBasketDto initializeBasket(VendingBasketDto basket);

    TakeAwayBasketDto makePayment(Double amountPaid, VendingBasketDto basket);

    List<Product> getStockList();

    Double showBasketTotal(VendingBasketDto basket);
}
