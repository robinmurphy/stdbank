package com.sbg.automation.vending;

import com.sbg.automation.vending.utils.CollectionMapperImpl;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringUnitTestConfiguration {

    @Bean
    public CollectionMapperImpl createCollectionMapper() {

        return new CollectionMapperImpl();
    }

    @Bean
    public DozerBeanMapper dozerMapper() {
        return new DozerBeanMapper();
    }

}
