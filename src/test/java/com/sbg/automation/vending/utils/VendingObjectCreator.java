package com.sbg.automation.vending.utils;

import com.sbg.automation.vending.jpa.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class VendingObjectCreator {

    public static List<Product> createProductList() {

        ArrayList<Product> productList = new ArrayList();

        productList.add(new Product(1L, "smarties", 02.00, 5));
        productList.add(new Product(2L, "coke", 1.00, 2));
        productList.add(new Product(3L, "chips", 3.30, 10));
        productList.add(new Product(4L, "water", 5.70, 23));
        productList.add(new Product(5L, "biltong", 5.00, 2));
        productList.add(new Product(6L, "fanta", 2.50, 1));
        productList.add(new Product(7L, "muffin", 6.00, 0));

        return productList;
    }
}
