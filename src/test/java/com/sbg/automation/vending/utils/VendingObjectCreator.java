package com.sbg.automation.vending.utils;

import com.sbg.automation.vending.jpa.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class VendingObjectCreator {

    public static List<Product> createProductList() {

        ArrayList<Product> productList = new ArrayList();

        productList.add(new Product("001", "smarties", 02.00, 5));
        productList.add(new Product("002", "coke", 1.00, 2));
        productList.add(new Product("003", "chips", 3.30, 10));
        productList.add(new Product("004", "water", 5.70, 23));
        productList.add(new Product("005", "biltong", 5.00, 2));
        productList.add(new Product("006", "fanta", 2.50, 1));
        productList.add(new Product("007", "muffin", 6.00, 0));

        return productList;
    }
}
