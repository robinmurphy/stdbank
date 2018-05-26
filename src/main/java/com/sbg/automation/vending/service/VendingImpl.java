package com.sbg.automation.vending.service;

import com.sbg.automation.vending.dto.TakeAwayBasketDto;
import com.sbg.automation.vending.dto.VendingBasketDto;
import com.sbg.automation.vending.jpa.entity.Product;
import com.sbg.automation.vending.jpa.repo.ProductRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service
public class VendingImpl implements Vending {

    private static final Logger LOGGER = Logger.getLogger(VendingImpl.class);

    @Autowired
    ProductRepo productRepo;

    @Override
    public VendingBasketDto updateBasket(VendingBasketDto basket) {
        return null;
    }

    @Override
    public TakeAwayBasketDto makePayment(Double amountPaid, VendingBasketDto basket) {
        return null;
    }

    @Override
    public List<Product> getStockList() {
        return  productRepo.findAll();
    }

}
