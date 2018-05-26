package com.sbg.automation.vending.spring;

import org.dozer.DozerBeanMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMBeanExport
@EnableCaching
public class ServiceConfiguration {

    @Bean
    public DozerBeanMapper dozerMapper() {
        List<String> mappingFiles = Arrays.asList(new String[]{"dozer_mappings.xml"});
        DozerBeanMapper mapper = new DozerBeanMapper(mappingFiles);
        return mapper;
    }

}
