package com.example.thesmallticket.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.thesmallticket.model.Utente;
// import com.example.thesmallticket.services.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.thesmallticket.repositories.*;



@RestController
@RequestMapping("/")
public class UtenteController {
    // @Autowired
    // UtenteService utenteService;
    @Autowired
    UtenteRepository utenteRepository;

    @GetMapping("/getAllUtenti")
    public ResponseEntity<List<Utente>> getAllUtenti(){
        try {
            List<Utente> utenteList = new ArrayList<>();
            utenteRepository.findAll().forEach(utenteList::add);

            if (utenteList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(utenteList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getUtenteById/{id}")
    public ResponseEntity<Utente> getUtenteById(@PathVariable Long id){
        Optional<Utente> utenteData = utenteRepository.findById(id);

        if(utenteData.isPresent()){
            return new ResponseEntity<>(utenteData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    
    @PostMapping("/addUtente")
    public ResponseEntity<Utente> addBook(@RequestBody Utente utente){
        Utente utenteObj = utenteRepository.save(utente);

        return new ResponseEntity<>(utenteObj, HttpStatus.OK);
    }
    
    @PostMapping("/updateUtenteById/{id}")
    public ResponseEntity<Utente> updateUtenteById(@PathVariable Long id, @RequestBody Utente newUtenteData){
        Optional<Utente> oldUtenteData = utenteRepository.findById(id);

        if (oldUtenteData.isPresent()) {
            Utente updatedUtenteData = oldUtenteData.get();
            updatedUtenteData.setUsername(newUtenteData.getUsername());
            updatedUtenteData.setPassword(newUtenteData.getPassword());
            updatedUtenteData.setEmail(newUtenteData.getEmail());

            Utente utenteObj = utenteRepository.save(updatedUtenteData);
            return new ResponseEntity<>(utenteObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleUtenteById/{id}")
    public ResponseEntity<Object> deleteUtenteById(@PathVariable Long id){
        utenteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    
}
