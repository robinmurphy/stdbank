package com.sbg.automation.vending.rest;

import com.sbg.automation.vending.SpringUnitTestConfiguration;
import com.sbg.automation.vending.dto.ProductDto;
import com.sbg.automation.vending.jpa.repo.ProductRepo;
import com.sbg.automation.vending.service.VendingImpl;
import com.sbg.automation.vending.utils.VendingObjectCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringUnitTestConfiguration.class, VendingRestEndpointsTest.Config.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VendingRestEndpointsTest {

    @Configuration
    public static class Config {

        @Bean
        public RestTemplate createRestTemplate() {
            return new RestTemplate();
        }

        @Bean
        public VendingImpl createVendingBean(){
            return new VendingImpl();
        }

        @Bean
        public EmbeddedServletContainerFactory servletContainer() {

            TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
            factory.setPort(8081);
            return factory;
        }

        @Bean
        public ProductRepo createProductRepoBean() {

            return Mockito.mock(ProductRepo.class);
        }
    }

    @Autowired
    private VendingImpl vending;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    RestTemplate restTemplate;

    @Before
    public void init() {

//        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

//    @Test
    public void test_load_vending_stock() throws Exception {

        TestRestTemplate testRestTemplate = new TestRestTemplate();

        when(vending.getStockList()).thenReturn(VendingObjectCreator.createProductList());
        when(productRepo.findAll()).thenReturn(VendingObjectCreator.createProductList());

        List<ProductDto> response = testRestTemplate.getForObject("http://localhost:8081/vending/loadstock", ArrayList.class);
//        String response = testRestTemplate.getForObject("http://127.0.0.1:9000/loadstock", String.class);


//        mockMvc.perform(
//                get("/vending/loadstock"))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn();
    }


}
