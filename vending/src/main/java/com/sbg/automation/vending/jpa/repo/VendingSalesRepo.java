package com.sbg.automation.vending.jpa.repo;

import com.sbg.automation.vending.jpa.entity.VendingSales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendingSalesRepo extends JpaRepository<VendingSales, Long> {
}
