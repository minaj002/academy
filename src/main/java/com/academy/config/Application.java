package com.academy.config;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"com.academy"})
@EnableMongoRepositories(basePackages = {"com.academy"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }




}
