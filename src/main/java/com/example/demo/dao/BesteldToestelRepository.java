package com.example.demo.dao;

import com.example.demo.domain.BesteldToestel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BesteldToestelRepository extends CrudRepository<BesteldToestel,Long> {
    
    List<BesteldToestel> findAll();

    BesteldToestel findByBesteldToestelId(long besteldToestelId);

}
