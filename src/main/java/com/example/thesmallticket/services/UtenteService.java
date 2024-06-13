package com.example.thesmallticket.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thesmallticket.model.Utente;
import com.example.thesmallticket.repositories.*;


import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente getUtenteById(Long id) {
        Optional<Utente> optional = utenteRepository.findById(id);
        Utente utente = new Utente();
        if (optional.isPresent()) {
            utente = optional.get();
            return utente;
        } else {
            utente = null;
            return utente;
        }
    }

    // public List<Utente> findAll() {
    //     return utenteRepository.findAll();
    // }

    // public Optional<Utente> findById(Long id) {
    //     return utenteRepository.findById(id);
    // }

    // public Utente save(Utente utente) {
    //     return utenteRepository.save(utente);
    // }

    // public void deleteById(Long id) {
    //     utenteRepository.deleteById(id);
    // }
}