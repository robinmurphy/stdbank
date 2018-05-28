package com.sbg.automation.vending.spring;

import liquibase.integration.spring.SpringLiquibase;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableConfigurationProperties(LiquibaseProperties.class)
public class ServiceConfiguration {

    @Bean
    public static BeanFactoryPostProcessor schemaDependencyProcessor() {
        return new BeanFactoryPostProcessor() {

            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
                BeanDefinition beanDef = beanFactory.getBeanDefinition("liquibase");
                List<String> deps = new ArrayList<String>();
                if (beanDef.getDependsOn() != null) {
                    deps = Arrays.asList(beanDef.getDependsOn());
                }
                deps.add("schemaCreator");
                beanDef.setDependsOn(deps.toArray(new String[deps.size()]));
            }
        };
    }

    @Bean
    public DozerBeanMapper dozerMapper() {
        List<String> mappingFiles = Arrays.asList(new String[]{"dozer_mappings.xml"});
        DozerBeanMapper mapper = new DozerBeanMapper(mappingFiles);
        return mapper;
    }

    @Bean(name = "liquibase")
    public SpringLiquibase liquibase(javax.sql.DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/db-changelog-liquibase-master.xml");
        liquibase.setDataSource(dataSource);
        liquibase.setDefaultSchema("SBG");
        return liquibase;
    }

    @Bean
    @Autowired
    public SchemaCreator schemaCreator(javax.sql.DataSource dataSource, LiquibaseProperties liquibaseProperties) {
        if (EmbeddedDatabaseConnection.isEmbedded(dataSource)) {
            return new SchemaCreator(dataSource, liquibaseProperties.getDefaultSchema());
        }
        return null;
    }

}
