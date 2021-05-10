package com.example.demo;

import com.example.demo.domain.BesteldToestel;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;


@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
   

   @Override
   public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
       config.exposeIdsFor(BesteldToestel.class);
   }
}