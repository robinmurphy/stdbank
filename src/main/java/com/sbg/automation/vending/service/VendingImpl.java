package com.sbg.automation.vending.service;

import com.sbg.automation.vending.dto.TakeAwayBasketDto;
import com.sbg.automation.vending.dto.VendingBasketDto;
import com.sbg.automation.vending.jpa.entity.Product;
import com.sbg.automation.vending.jpa.entity.VendingSales;
import com.sbg.automation.vending.jpa.repo.ProductRepo;
import com.sbg.automation.vending.jpa.repo.VendingSalesRepo;
import com.sbg.automation.vending.payment.PaymentProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VendingImpl implements Vending {

    private static final Logger LOGGER = Logger.getLogger(VendingImpl.class);

    @Autowired
    ProductRepo productRepo;
    @Autowired
    VendingSalesRepo vendingSalesRepo;

    @Autowired
    PaymentProcessor paymentProcessor;

    @Override
    public TakeAwayBasketDto initializeBasket(VendingBasketDto basket) {

        //TODO: get the sequence number for id
        Long orderId = 1L;
        basket.setOrderId(orderId);
        TakeAwayBasketDto takeAwayBasket = new TakeAwayBasketDto();
        takeAwayBasket.setBasket(basket);
        takeAwayBasket.setBasketTotal( PaymentProcessor.calculateTotal(basket));

        return takeAwayBasket ;
    }

    @Override
    public TakeAwayBasketDto makePayment(Double amountPaid, VendingBasketDto basket) {

        TakeAwayBasketDto takeAwayBasket = paymentProcessor.calculateBasketTotals(basket, amountPaid);
        VendingSales vendingSales = new VendingSales(new Date(), takeAwayBasket.getBasketTotal(), takeAwayBasket.getBasket().getProducts().size());

        vendingSalesRepo.saveAndFlush(vendingSales);
        return takeAwayBasket;
    }

    @Override
    public List<Product> getStockList() {
        return productRepo.findAll();
    }

    @Override
    public Double showBasketTotal(VendingBasketDto basket) {
        return PaymentProcessor.calculateTotal(basket);
    }
}
