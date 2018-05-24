package com.sbg.automation.vending.jpa.repo;

import com.sbg.automation.vending.jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
