package com.sbg.automation.vending.payment;

import com.sbg.automation.vending.dto.TakeAwayBasketDto;
import com.sbg.automation.vending.dto.VendingBasketDto;
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
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

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

    VendingBasketDto vendingBasket;

    @MockBean
    ProductRepo productRepo;

    @Before
    public void init() {
        Product product = new Product();
    }

    @Test
    public void test_basket_total_amount() {

        when(productRepo.findAll()).thenReturn(createProductList());

        vendingBasket = new VendingBasketDto();
        vendingBasket.setOrderId(1L);
        vendingBasket.setProducts(productRepo.findAll());

        Double amountPaid = 90.20;

        TakeAwayBasketDto takeAwayBasketDto = paymentProcessor.calculateBasketTotals(vendingBasket, amountPaid);

        assertEquals("check change is correct accordingto amount paid", (amountPaid - PaymentProcessor.calculateTotal(vendingBasket)), takeAwayBasketDto.getChange());

    }

    @Test
    public void test_change_configuration() {

        Double changeReceived = 90.20;
        Map<String, Double> changeConfiguration = paymentProcessor.configurationCalculator(changeReceived);

        assertTrue("Change configuration", !ObjectUtils.isEmpty(changeConfiguration));
        assertEquals("change received is same as calculated denomination ", changeReceived, changeConfiguration.entrySet().stream().mapToDouble(o -> o.getValue()).sum());
    }


    List<Product> createProductList() {

        List<Product> productList = new ArrayList<>();

        Product product = new Product();
        product.setDescription("test1");
        product.setItemValue(3.60);
        product.setProductKey(1L);
        product.setQuantity(0);
        productList.add(product);

        Product product1 = new Product();
        product1.setDescription("test2");
        product1.setItemValue(15.00);
        product1.setProductKey(2L);
        product1.setQuantity(10);
        productList.add(product1);

        Product product2 = new Product();
        product2.setDescription("test3");
        product2.setItemValue(9.20);
        product2.setProductKey(3L);
        product2.setQuantity(10);
        productList.add(product2);

        Product product3 = new Product();
        product3.setDescription("test4");
        product3.setItemValue(5.50);
        product3.setProductKey(4L);
        product3.setQuantity(10);
        productList.add(product3);

        Product product4 = new Product();
        product4.setDescription("test5");
        product4.setItemValue(10.30);
        product4.setProductKey(5L);
        product4.setQuantity(10);
        productList.add(product4);

        return productList;
    }
}
