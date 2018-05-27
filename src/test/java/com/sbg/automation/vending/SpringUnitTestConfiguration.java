package com.sbg.automation.vending;

import com.sbg.automation.vending.utils.CollectionMapperImpl;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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
