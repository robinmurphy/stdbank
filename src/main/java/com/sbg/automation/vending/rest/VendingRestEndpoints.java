package com.sbg.automation.vending.rest;

import com.sbg.automation.vending.dto.ProductDto;
import com.sbg.automation.vending.dto.TakeAwayBasketDto;
import com.sbg.automation.vending.dto.VendingBasketDto;
import com.sbg.automation.vending.exception.InvalidPaymentException;
import com.sbg.automation.vending.payment.PaymentProcessor;
import com.sbg.automation.vending.service.Vending;
import com.sbg.automation.vending.utils.CollectionMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VendingRestEndpoints {

    private static final Logger LOGGER = Logger.getLogger(VendingRestEndpoints.class);

    @Autowired
    Vending vending;
    @Autowired
    CollectionMapper beanMapper;

    @ApiOperation(value = "Update the basket")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request was processed successfully", response = VendingBasketDto.class),
            @ApiResponse(code = 400, message = "Parameters supplied were either missing or invalid")})
    @PostMapping(path = "/vending/updatebasket", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Double updateBasket(@RequestBody VendingBasketDto basket) {

        return PaymentProcessor.calculateTotal(vending.updateBasket(basket));
    }

    @ApiOperation(value = "checkout & pay for basket")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request was processed successfully", response = TakeAwayBasketDto.class),
            @ApiResponse(code = 400, message = "Parameters supplied were either missing or invalid")})
    @PostMapping(path = "/vending/checkout", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TakeAwayBasketDto checkoutBasket(@Valid @RequestParam("amountPaid") Double amountPaid, @RequestBody VendingBasketDto basket) {

        if (amountPaid < PaymentProcessor.calculateTotal(basket)) {
            throw new InvalidPaymentException("need more money - :" + (PaymentProcessor.calculateTotal(basket) - amountPaid));
        }
        return vending.makePayment(amountPaid, basket);
    }

    @ApiOperation(value = "loadStock")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request was processed successfully", response = TakeAwayBasketDto.class),
            @ApiResponse(code = 400, message = "Parameters supplied were either missing or invalid")})
    @GetMapping(path = "/vending/loadstock", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ProductDto> loadProducts() {

        return beanMapper.mapAsList(vending.getStockList(), ProductDto.class);
    }

    @ApiOperation(value = "", hidden = true)
    @GetMapping({"/swagger", "/vending", "/vending/swagger"})
    public RedirectView index() {

        return new RedirectView("/swagger-ui.html");
    }
}

