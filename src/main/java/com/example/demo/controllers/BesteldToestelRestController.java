package com.example.demo.controllers;

import com.example.demo.dao.BesteldToestelRepository;
import com.example.demo.domain.BesteldToestel;
import com.example.demo.service.BesteldToestelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8888", maxAge = 3600, allowCredentials = "true")
@RequestMapping(path = "/besteldtoestel",produces = "application/json")
public class BesteldToestelRestController {

    @Autowired
    private BesteldToestelRepository besteldToestelRepo;
    @Autowired
    protected BesteldToestelService besteldToestelService; // ready for dependency injection

    @RequestMapping(path={"/besteldetoestellen"},method= RequestMethod.GET)
    //@GetMapping("//besteldetoestellen")
    public @ResponseBody
    List<BesteldToestel> getBesteldeToestellen(){
        return besteldToestelService.getBesteldeToestellen();
    }

//    @RequestMapping(value={"/besteldtoesteldetails/{id}"},method=RequestMethod.GET)
//    public @ResponseBody BesteldToestel getToestel(@PathVariable("id") Integer id) {
//        System.out.println("id = " + id);
//        return besteldToestelService.getBesteldToestelById(id);
//    }

    @GetMapping("/{id}")
    public BesteldToestel getToestelById(@PathVariable("id") Long id) {
        if (besteldToestelRepo.findById(id).isPresent()) {
            return besteldToestelRepo.findById(id).get();
        } else {
            return  null;
        }
    }

    @PostMapping(path = "/deleteBesteldToestel",consumes = "application/json")
    public String deleteToestel( @RequestBody BesteldToestel toestel) {
        try {
            besteldToestelService.deleteBesteldToestel(toestel.getbesteldToestelId());
        } catch (Exception e) {
            return "Something went wrong :"+ e.getMessage();
        }
        return "Successfully deleted entry with id "+toestel.getbesteldToestelId();
    }
    
    // REST POST ... Aanmaken van een bestelde toestel met doorgegeven object
    @PostMapping(path={"/createBesteldToestel"},consumes = "application/json")
    public String processNieuwtoestel(@RequestBody BesteldToestel toestel, Errors errors) {

        StringBuilder message= new StringBuilder();

        try {
            // Are there any input validation errors detected by JSR 380 bean validation?
            if (errors.hasErrors() ) {
                message = new StringBuilder("Correct input errors, please: <br>");
                for (ObjectError objectError: errors.getAllErrors()) {
                    message.append(objectError.getDefaultMessage()).append("<br>");
                }
                throw new IllegalArgumentException();
            }

            // Now that the input seems to be OK, let's create a new entry or update/delete an existing entry
            message = new StringBuilder(String.valueOf(besteldToestelService.createBesteldToestel(toestel)));

        } catch (IllegalArgumentException e) {
            // Nothing special needs to be done
        }
        return message.toString();

    }


    //Test werkt zonder prijs
    // REST PUT ... breng de toestand van bestaande resource van de client naar de server
    @RequestMapping(value={"/updatebesteldtoestel"},method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putToestel( Integer id, String naam, Double prijs, Long aankoopdatumMillis, Integer planningId){
        BesteldToestel toestel = new BesteldToestel();
        toestel.setBesteldToestelId(id);
        toestel.setNaam(naam);
        if(aankoopdatumMillis != null){
            Date aankoopdatum = new Date(aankoopdatumMillis);
            toestel.setAankoopdatum(aankoopdatum);
        }
        if(planningId != null){
            toestel.setPlanningId(planningId);
        }
        toestel.setPrijs(prijs);
        besteldToestelService.UpdateBesteldToestel(toestel);
    }

}
