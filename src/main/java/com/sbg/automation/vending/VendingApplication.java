package com.sbg.automation.vending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan("com.sbg")
public class VendingApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendingApplication.class, args);
    }
}
