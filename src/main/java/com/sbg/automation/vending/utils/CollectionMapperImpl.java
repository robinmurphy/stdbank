package com.sbg.automation.vending.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionMapperImpl implements CollectionMapper {

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Override
    public <T> List<T> mapAsList(List<?> sourceList, Class<T> destClass) {

        List<T> returnList = sourceList.stream()
                .map(o -> dozerBeanMapper.map(o, destClass))
                .collect(Collectors.toList());

        return returnList;
    }

    @Override
    public <T> T map(Object o, Class<T> aClass) throws MappingException {
        return null;
    }

    @Override
    public void map(Object o, Object o1) throws MappingException {

    }

    @Override
    public <T> T map(Object o, Class<T> aClass, String s) throws MappingException {
        return null;
    }

    @Override
    public void map(Object o, Object o1, String s) throws MappingException {

    }
}
