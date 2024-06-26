package com.example.thesmallticket.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.thesmallticket.model.Utente;
import com.example.thesmallticket.repositories.UtenteRepository;

@Controller
public class UtenteController {

    @Autowired
    UtenteRepository utenteRepository;

    // @GetMapping("/eventi")
    // public String home() {
    //     return "index";
    // }

    // @GetMapping("/home")
    // public String home2() {
    //     return "index";
    // }

    @GetMapping("/getAllUtenti")
    public ResponseEntity<List<Utente>> getAllUtenti() {
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

    @GetMapping("/utenti")
    public String getUsers(Model model) {
        // Ottieni la lista degli utenti dal servizio
        List<Utente> utenti = utenteRepository.findAll();

        // Aggiungi la lista degli utenti al modello
        model.addAttribute("utenti", utenti);

        // Restituisci il nome del template Thymeleaf (senza l'estensione .html)
        return "userList";
    }

    @GetMapping("/getUtenteById/{id}")
    public ResponseEntity<Utente> getUtenteById(@PathVariable Long id) {
        Optional<Utente> utenteData = utenteRepository.findById(id);

        if (utenteData.isPresent()) {
            return new ResponseEntity<>(utenteData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/addUtente")
    public ResponseEntity<Utente> addUtente(@RequestBody Utente utente) {
        Utente utenteObj = utenteRepository.save(utente);

        return new ResponseEntity<>(utenteObj, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUtenteById/{id}")
    public ResponseEntity<Object> deleteUtenteById(@PathVariable Long id) {
        utenteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint per visualizzare la pagina HTML per aggiungere un utente
    @GetMapping("/addUtentePage")
    public String addUtentePage(Utente utente, Model model) {
        model.addAttribute("utente", utente);
        return "addUtente";
    }

    // Endpoint per gestire l'invio del form HTML
    @PostMapping("/saveUtente")
    public String saveUtente(@ModelAttribute Utente utente) {
        utenteRepository.save(utente);
        return "redirect:/eventi"; // Redirect alla pagina che mostra tutti gli utenti
    }

    @PostMapping("/updateUtenteById/{id}")
    public ResponseEntity<Utente> updateUtenteById(@PathVariable Long id, @RequestBody Utente newUtenteData) {
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

    // Endpoint per visualizzare la pagina HTML per aggiornare un utente
    @GetMapping("/updateUtentePage/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Optional<Utente> utente = utenteRepository.findById(id);
        if (utente.isPresent()) {
            model.addAttribute("utente", utente.get());
            return "updateUtente";
        } else {
            return "redirect:/utenti";
        }
    }

    // Endpoint per gestire l'invio del form di aggiornamento utente
    @PostMapping("/updateUtente/{id}")
    public String updateUtente(@PathVariable Long id, @ModelAttribute Utente utente) {
        Optional<Utente> oldUtenteData = utenteRepository.findById(id);

        if (oldUtenteData.isPresent()) {
            Utente updatedUtenteData = oldUtenteData.get();
            updatedUtenteData.setUsername(utente.getUsername());
            updatedUtenteData.setPassword(utente.getPassword());
            updatedUtenteData.setEmail(utente.getEmail());

            utenteRepository.save(updatedUtenteData);
        }
        return "redirect:/utenti";
    }
}
