package com.sbg.automation.vending.utils;

import org.dozer.Mapper;

import java.util.List;

public interface CollectionMapper extends Mapper {

    <T> List<T> mapAsList(List<?> sourceList, Class<T> destClass);
}
