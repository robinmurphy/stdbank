package com.sbg.automation.vending.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbg.automation.vending.SpringUnitTestConfiguration;
import com.sbg.automation.vending.dto.VendingBasketDto;
import com.sbg.automation.vending.jpa.repo.ProductRepo;
import com.sbg.automation.vending.service.VendingImpl;
import com.sbg.automation.vending.utils.VendingObjectCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.TimeUnit;

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
        public EmbeddedServletContainerFactory servletContainer() {

            TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
            factory.setPort(9000);
            factory.setSessionTimeout(10, TimeUnit.MINUTES);
            return factory;
        }

        @Bean
        public ProductRepo createProductRepoBean() {

            return Mockito.mock(ProductRepo.class);
        }
    }

    @MockBean
    private VendingImpl vending;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {

//        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

//    @Test
    public void test_load_vending_stock() throws Exception {

        TestRestTemplate testRestTemplate = new TestRestTemplate();

//        when(productRepo.findAll()).thenReturn(VendingObjectCreator.createProductList());
        when(vending.getStockList()).thenReturn(VendingObjectCreator.createProductList());

        VendingBasketDto basketDto = new VendingBasketDto();
        basketDto.setOrderId(234L);
        basketDto.setProducts(VendingObjectCreator.createProductList());

        testRestTemplate.getForObject("http://127.0.0.1:9000/loadstock", VendingBasketDto.class);
//        String response = testRestTemplate.getForObject("http://127.0.0.1:9000/loadstock", String.class);
//        System.out.println(response);


//        mockMvc.perform(
//                get("/vending/loadstock"))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn();
    }


}
