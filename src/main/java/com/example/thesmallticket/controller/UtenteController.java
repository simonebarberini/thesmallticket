package com.example.thesmallticket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.thesmallticket.model.Utente;
import com.example.thesmallticket.services.*;

import org.springframework.web.bind.annotation.PostMapping;

import com.example.thesmallticket.repositories.*;



@RestController
@RequestMapping("/api/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private UtenteRepository utenteRepository;
    
    @GetMapping
    public String getAllUtenti(Model model){
        List<Utente> listaUtenti = utenteRepository.findAll();
        model.addAttribute("utente", listaUtenti);
        return "utente";
    }

    @GetMapping("/{id}")
    public String getUtenteById(@PathVariable("id") Long id, Model model) {
        Optional<Utente> utente = utenteService.findById(id);
        if (utente.isPresent()) {
            model.addAttribute("utente", utente);
            return "utente";
        }
        return "redirect:/api/utenti" ;
    }

    @PostMapping("/aggiungiUtente")
    public String creaUtente(Utente utente) {
        utenteRepository.save(utente);
        return "redirect:/${utente.}";
    }
    
    
}
