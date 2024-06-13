package com.example.thesmallticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
        return new String();
    }

    @PostMapping("/aggiungiUtente")
    public String creaUtente(Utente utente) {
        utenteRepository.save(utente);
        return "redirect:/${utente.idUtente}";
    }
    
    
}
