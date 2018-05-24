package com.sbg.automation.vending.rest;


import com.sbg.automation.vending.dto.VendingBasket;
import com.sbg.automation.vending.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class VendingRestEndpoints {

    @Autowired
    Vending vending;

    //updateBasket
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updateBasket(@RequestBody VendingBasket basket) {

//        vending.updateBasket(basket);
    }

    //make payment -> return change
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void checkoutBasket(@PathVariable Double amountPaid, @RequestBody VendingBasket basket) {

//        vending.makePayment();
    }

}
