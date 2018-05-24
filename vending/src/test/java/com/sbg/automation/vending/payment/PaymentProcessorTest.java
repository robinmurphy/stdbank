package com.sbg.automation.vending.payment;

import com.sbg.automation.vending.dto.VendingBasket;
import com.sbg.automation.vending.jpa.entity.Product;
import com.sbg.automation.vending.jpa.repo.ProductRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PaymentProcessorTest.Config.class})
public class PaymentProcessorTest {


    public static class Config {

        @Bean
        public PaymentProcessor createPaymentProcessor() {
            return new PaymentProcessor();
        }

        @Bean
        public ProductRepo createProductRepo() {
            return Mockito.mock(ProductRepo.class);
        }
    }

    @Autowired
    PaymentProcessor paymentProcessor;

    VendingBasket vendingBasket;

    @MockBean
    ProductRepo productRepo;

    @Before
    public void init() {
        Product product = new Product();
    }

    @Test
    public void test_basket_total_amount() {

        when(productRepo.findAll()).thenReturn(createProductList());

        vendingBasket = new VendingBasket();
        vendingBasket.setOrderId(1L);
        vendingBasket.setProducts(productRepo.findAll());

        Double total = paymentProcessor.calculateTotal(vendingBasket);

        assertEquals(" amount expected", new Double(30), total);

    }


    List<Product> createProductList() {

        List<Product> productList = new ArrayList<>();

        Product product = new Product();
        product.setDescription("test1");
        product.setItemValue(new Double(10));
        product.setProductKey("test1");
        product.setQuantity(0);
        productList.add(product);

        Product product1 = new Product();
        product1.setDescription("test2");
        product1.setItemValue(new Double(0));
        product1.setProductKey("test2");
        product1.setQuantity(10);
        productList.add(product1);

        Product product2 = new Product();
        product2.setDescription("test3");
        product2.setItemValue(new Double(10));
        product2.setProductKey("test3");
        product2.setQuantity(10);
        productList.add(product2);

        Product product3 = new Product();
        product3.setDescription("test4");
        product3.setItemValue(new Double(10));
        product3.setProductKey("test4");
        product3.setQuantity(10);
        productList.add(product3);

        Product product4 = new Product();
        product4.setDescription("test5");
        product4.setItemValue(new Double(10));
        product4.setProductKey("test5");
        product4.setQuantity(10);
        productList.add(product4);

        return productList;
    }
}
