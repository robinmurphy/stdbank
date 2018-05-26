package com.sbg.automation.vending.utils;


import com.sbg.automation.vending.SpringUnitTestConfiguration;
import com.sbg.automation.vending.dto.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringUnitTestConfiguration.class})
public class CollectionMapperTest {

    @Autowired
    CollectionMapper collectionMapper;

    @Test
    public void test_dozer_map_to_list() {

        List<ProductDto> productList = collectionMapper.mapAsList(VendingObjectCreator.createProductList(), ProductDto.class);
        assertEquals("list size", 7, productList.size());
        assertTrue("objects are of ProductDTO type", productList.get(0) instanceof ProductDto);
    }

}


