package com.example.demo.dao;

import com.example.demo.domain.BesteldToestel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BesteldToestelRepository extends CrudRepository<BesteldToestel,Long> {
    
    List<BesteldToestel> findAll();

    BesteldToestel findByBesteldToestelId(long besteldToestelId);

}
