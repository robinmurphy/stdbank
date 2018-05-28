package com.sbg.automation.vending;

import com.sbg.automation.vending.dto.TakeAwayBasketDto;
import com.sbg.automation.vending.dto.VendingBasketDto;
import com.sbg.automation.vending.jpa.entity.Product;
import com.sbg.automation.vending.jpa.repo.DbUtil;
import com.sbg.automation.vending.jpa.repo.ProductRepo;
import com.sbg.automation.vending.jpa.repo.VendingSalesRepo;
import com.sbg.automation.vending.payment.PaymentProcessor;
import com.sbg.automation.vending.service.VendingImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringUnitTestConfiguration.class, VendingApplicationTests.Config.class})
public class VendingApplicationTests {

    public static class Config {

        @Bean
        public VendingImpl createVendingBean() {
            return new VendingImpl();
        }

        @Bean
        public ProductRepo createProductRepo() {
            return Mockito.mock(ProductRepo.class);
        }

        @Bean
        public VendingSalesRepo createVendingSalesRepo() {
            return Mockito.mock(VendingSalesRepo.class);
        }

        @Bean
        public DbUtil createDbUtil() {
            return Mockito.mock(DbUtil.class);
        }

        @Bean
        public PaymentProcessor createPaymentProcessor() {
            return new PaymentProcessor();
        }
    }

    @Autowired
    VendingImpl vending;
    @MockBean
    DbUtil dbUtil;
    @Autowired
    PaymentProcessor paymentProcessor;

    @Ignore("not yet implemented")
    @Test
    public void test_load_products() {

    }

    @Test
    public void test_checkout_basket() {

        Double amountPaid = new Double(93.70);
        TakeAwayBasketDto takeAwayBasketDto = vending.makePayment(amountPaid, createVendingBasket());

        assertEquals(takeAwayBasketDto.getChange(), new Double((amountPaid - PaymentProcessor.calculateTotal(createVendingBasket()))));
    }

    @Test
    public void test_initialize_basket() {

        TakeAwayBasketDto takeAwayBasketDto = vending.initializeBasket(createVendingBasket());

        assertEquals(takeAwayBasketDto.getBasketTotal(), PaymentProcessor.calculateTotal(createVendingBasket()));
        assertEquals(takeAwayBasketDto.getBasket().getProducts().size(), createVendingBasket().getProducts().size());
    }

    private VendingBasketDto createVendingBasket() {

        VendingBasketDto vendingBasket = new VendingBasketDto();

        vendingBasket.setOrderId(1L);
        vendingBasket.setProducts(createProductList());

        return vendingBasket;
    }

    private List<Product> createProductList() {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(100L, "smarties", 2.40, 10));
        productList.add(new Product(101L, "coke", 12.40, 10));
        productList.add(new Product(102L, "chips", 9.30, 10));
        productList.add(new Product(103L, "popcorn", 15.70, 10));
        productList.add(new Product(104L, "banana", 1.40, 10));

        return productList;
    }

}
