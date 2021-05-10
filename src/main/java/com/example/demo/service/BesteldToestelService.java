package com.example.demo.service;

import com.example.demo.domain.BesteldToestel;
import java.util.List;

public interface BesteldToestelService {

    // READ
       BesteldToestel getBesteldToestelById(long besteldToestelId);

       List<BesteldToestel> getBesteldeToestellen();

    // CREATE

    BesteldToestel createBesteldToestel( BesteldToestel besteldToestel);

    // UPDATE

    void UpdateBesteldToestel(BesteldToestel toestel);

    // DELETE
    void deleteBesteldToestel(long besteldToestelId);

     

 

  

}
