package com.example.thesmallticket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.thesmallticket.model.Utente;
import com.example.thesmallticket.services.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.thesmallticket.repositories.*;



@RestController
@RequestMapping("/")
public class UtenteController {
    @Autowired
    UtenteService utenteService;
    @Autowired
    UtenteRepository utenteRepository;
    
    @GetMapping
    public List<Utente> getAllUtenti(){
        return utenteRepository.findAll();
    }

    // @GetMapping("/aggiungiUtente")
    // public String formUtente(Model model) {
    //     Utente utente = new Utente();
    //     model.addAttribute("utente", utente);
    //     return "aggiungiutente";
    // }


    // @GetMapping("/{id}")
    // public String getUtenteById(@PathVariable("id") Long id, Model model) {
    //     Utente utente = utenteService.getUtenteById(id);
    //     model.addAttribute("utente", utente);
    //     return "utentesingolo";
       
    // }

    @PostMapping()
    public Utente creaUtente(@RequestBody Utente utente) {
        return utenteRepository.save(utente);
    }
    
    
}
