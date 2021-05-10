package com.example.demo.service;

import com.example.demo.dao.BesteldToestelRepository;
import com.example.demo.domain.BesteldToestel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service("besteldToestelService")
public class BesteldToestelServiceImp implements  BesteldToestelService {
    final
    BesteldToestelRepository besteldToestelRepository;


    public BesteldToestelServiceImp(BesteldToestelRepository besteldToestelRepository) {
        this.besteldToestelRepository = besteldToestelRepository;
    }

    @Override
    public void deleteBesteldToestel(long besteldToestelId) {
        BesteldToestel toestel = besteldToestelRepository.findByBesteldToestelId(besteldToestelId);
        if(toestel != null) besteldToestelRepository.delete(toestel);
    }


    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public BesteldToestel createBesteldToestel(BesteldToestel toestel) {
        return besteldToestelRepository.save(toestel);
    }
    @Override
    public void UpdateBesteldToestel(BesteldToestel toestel) {
        BesteldToestel toEdit = besteldToestelRepository.findByBesteldToestelId(toestel.getbesteldToestelId());
        toEdit.setNaam(toestel.getNaam());
        toEdit.setAankoopdatum(toestel.getAankoopdatum());
        toEdit.setPrijs(toestel.getPrijs());
        besteldToestelRepository.save(toEdit);
    }

    @Override
    public BesteldToestel getBesteldToestelById(long besteldToestelId) {
        return besteldToestelRepository.findByBesteldToestelId(besteldToestelId);
    }

    @Override
    public List<BesteldToestel> getBesteldeToestellen() {
        return  besteldToestelRepository.findAll();
    }

   
}
