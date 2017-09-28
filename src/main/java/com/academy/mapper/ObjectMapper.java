package com.academy.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapper {

    @Autowired
    private MapperFactory mapperFactory;

    public  MapperFactory getFactory(){
        return mapperFactory;
    }

    public MapperFacade getFacade(){
        return mapperFactory.getMapperFacade();
    }


}
