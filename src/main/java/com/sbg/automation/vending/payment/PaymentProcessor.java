package com.sbg.automation.vending.payment;

import com.sbg.automation.vending.dto.TakeAwayBasketDto;
import com.sbg.automation.vending.dto.VendingBasketDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentProcessor {

    private List<Double> configurations;
    private static final Double ZERO = new Double(0.00);

    @PostConstruct
    public void load() {

        configurations = new ArrayList<>();
        configurations.add(new Double(50));
        configurations.add(new Double(20));
        configurations.add(new Double(10));
        configurations.add(new Double(5));
        configurations.add(new Double(2));
        configurations.add(new Double(1));
        configurations.add(new Double(0.5));
        configurations.add(new Double(0.2));
        configurations.add(new Double(0.1));
    }

    public TakeAwayBasketDto calculateBasketTotals(VendingBasketDto basket, Double amountPaid) {

        TakeAwayBasketDto takeAwayBasketDto = new TakeAwayBasketDto();
        takeAwayBasketDto.setChange(calculateChange(calculateTotal(basket), amountPaid));
        takeAwayBasketDto.setChangeConfiguration(configurationCalculator(takeAwayBasketDto.getChange()));
        takeAwayBasketDto.setBasketTotal(calculateTotal(basket));
        takeAwayBasketDto.setBasket(basket);

        return takeAwayBasketDto;
    }

    public static Double calculateTotal(VendingBasketDto basket) {

        return basket.getProducts()
                .stream()
                .filter(o -> o.getItemValue() != null)
                .mapToDouble(o -> o.getItemValue()).sum();
    }

    private Double calculateChange(Double basketvalue, Double amountPaid) {

        return amountPaid - basketvalue;
    }

    Map<String, Double> configurationCalculator(Double changeAmount) {

        Map<String, Double> changeConfiguration = new HashMap<>();

        for (Double item : configurations) {
            Double amt = new Double(ZERO);

            while (changeAmount >= item && !changeAmount.equals(ZERO)) {
                int value = new BigDecimal(changeAmount / item).intValue();
                amt = amt + item;
                changeAmount = new BigDecimal(changeAmount % amt).setScale(1, BigDecimal.ROUND_HALF_EVEN).doubleValue();
                changeConfiguration.put("R" + item.toString(), amt * value);
            }
        }
        return changeConfiguration;
    }
}
