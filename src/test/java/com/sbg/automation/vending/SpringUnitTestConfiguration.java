package com.sbg.automation.vending;

import com.sbg.automation.vending.utils.CollectionMapperImpl;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringUnitTestConfiguration {

    @Bean
    public CollectionMapperImpl createCollectionMapper() {

        return new CollectionMapperImpl();
    }

    @Bean
    public DozerBeanMapper dozerMapper() {
        List<String> mappingFiles = Arrays.asList(new String[]{"dozer_mappings.xml"});
        DozerBeanMapper mapper = new DozerBeanMapper(mappingFiles);
        return mapper;
    }

}
