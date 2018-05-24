package com.sbg.automation.vending.rest;


import com.sbg.automation.vending.service.*;
import org.springframework.beans.factory.annotation.*;

public class VendingRestEndpoints {

    @Autowired
    Vending vending;

    //updateBasket
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updateBasket(@RequestBody VendingBasket basket) {

        vending.updateBasket(basket);
    }

    //make payment -> return change
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void checkoutBasket(@PathVariable Double amountPaid, @RequestBody VendingBasket basket) {

        vending.makePayment();
    }

}
