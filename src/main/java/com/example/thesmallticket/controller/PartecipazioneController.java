package com.example.thesmallticket.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.thesmallticket.model.Evento;
import com.example.thesmallticket.model.Partecipazione;
import com.example.thesmallticket.model.Utente;
import com.example.thesmallticket.repositories.EventoRepository;
import com.example.thesmallticket.repositories.PartecipazioneRepository;
import com.example.thesmallticket.repositories.UtenteRepository;

@Controller
public class PartecipazioneController {

    @Autowired
    private PartecipazioneRepository partecipazioneRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @PostMapping("/addPartecipazione")
    public ResponseEntity<Partecipazione> aggiungiPartecipazione(@RequestParam Long id, @RequestParam Long idUtente) {
        Optional<Evento> evento = eventoRepository.findById(id);
        Optional<Utente> utente = utenteRepository.findById(idUtente);

        if (evento.isPresent() && utente.isPresent()) {
            Partecipazione partecipazione = new Partecipazione();
            partecipazione.setEvento(evento.get());
            partecipazione.setUtente(utente.get());
            partecipazioneRepository.save(partecipazione);
            return new ResponseEntity<>(partecipazione,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/partecipanti/{eventoId}")
    public String getPartecipanti(@PathVariable Long eventoId, Model model) {
        List<Partecipazione> partecipazioni = partecipazioneRepository.findByEventoId(eventoId);
        model.addAttribute("partecipazioni", partecipazioni);
        return "partecipanti";
    }
}
