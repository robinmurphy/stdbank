package com.sbg.automation.vending.rest;

import com.sbg.automation.vending.dto.ProductDto;
import com.sbg.automation.vending.dto.TakeAwayBasketDto;
import com.sbg.automation.vending.dto.VendingBasketDto;
import com.sbg.automation.vending.service.Vending;
import com.sbg.automation.vending.utils.CollectionMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/vending")
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
    @RequestMapping(path = "updatebasket", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<VendingBasketDto> updateBasket(@RequestBody VendingBasketDto basket) {

        if (ObjectUtils.isEmpty(basket)) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(vending.updateBasket(basket), HttpStatus.OK);

    }

    @ApiOperation(value = "checkout & pay for basket")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request was processed successfully", response = TakeAwayBasketDto.class),
            @ApiResponse(code = 400, message = "Parameters supplied were either missing or invalid")})
    @RequestMapping(path = "/checkout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TakeAwayBasketDto> checkoutBasket(@PathVariable Double amountPaid, @RequestBody VendingBasketDto basket) {

        if (ObjectUtils.isEmpty(basket) || ObjectUtils.isEmpty(amountPaid)) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(vending.makePayment(amountPaid, basket), HttpStatus.OK);
    }

    @ApiOperation(value = "load vending machine stock")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request was processed successfully", response = ArrayList.class),
            @ApiResponse(code = 404, message = "No stock items found")})
    @RequestMapping(path = "/loadstock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<ProductDto>> loadProducts() {

        List<ProductDto> productList = beanMapper.mapAsList(vending.getStockList(), ProductDto.class);

        if (ObjectUtils.isEmpty(productList)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
