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
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import com.example.thesmallticket.model.Evento;
// import com.example.thesmallticket.services.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.thesmallticket.repositories.*;

@Controller
public class EventoController {
    // @Autowired
    // eventoService eventoService;
    @Autowired
    EventoRepository eventoRepository;

    @GetMapping("/")
    public String redirectToEventi() {
        return "redirect:/eventi";
    }

    @GetMapping("/getAllEventi")
    public ResponseEntity<List<Evento>> getAllUtenti() {
        try {
            List<Evento> eventoList = new ArrayList<>();
            eventoRepository.findAll().forEach(eventoList::add);

            if (eventoList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(eventoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/eventi")
    public String getEventi(Model model) {
        // Ottieni la lista degli utenti dal servizio
        List<Evento> eventi = eventoRepository.findAll();

        // Aggiungi la lista degli utenti al modello
        model.addAttribute("eventi", eventi);

        // Restituisci il nome del template Thymeleaf (senza l'estensione .html)
        return "index";
    }

    @GetMapping("/getEventoById/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        Optional<Evento> eventoData = eventoRepository.findById(id);

        if (eventoData.isPresent()) {
            return new ResponseEntity<>(eventoData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/addEvento")
    public ResponseEntity<Evento> addevento(@RequestBody Evento evento) {
        Evento eventoObj = eventoRepository.save(evento);

        return new ResponseEntity<>(eventoObj, HttpStatus.OK);
    }

    @PostMapping("/updateeventoById/{id}")
    public ResponseEntity<Evento> updateeventoById(@PathVariable Long id, @RequestBody Evento neweventoData) {
        Optional<Evento> oldeventoData = eventoRepository.findById(id);

        if (oldeventoData.isPresent()) {
            Evento updatedeventoData = oldeventoData.get();
            updatedeventoData.setNomeEvento(neweventoData.getNomeEvento());
            updatedeventoData.setLuogo(neweventoData.getLuogo());
            updatedeventoData.setData(neweventoData.getData());
            updatedeventoData.setCategoria(neweventoData.getCategoria());

            Evento eventoObj = eventoRepository.save(updatedeventoData);
            return new ResponseEntity<>(eventoObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleEventoById/{id}")
    public ResponseEntity<Object> deleteEventoById(@PathVariable Long id) {
        eventoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // endpoint per visualizzare la pagina HTML per aggiungere un evento
    @GetMapping("/addEventoPage")
    public String addEventoPage(Evento evento, Model model) {
        model.addAttribute("evento", evento);
        return "addEvento";
    }

    // endpoint per gestire l'invio del form HTML
    @PostMapping("/saveEvento")
    public String saveEvento(@ModelAttribute Evento evento) {
        eventoRepository.save(evento);
        return "redirect:/eventi"; // Redirect alla pagina che mostra tutti gli utenti
    }

    // @GetMapping("/home")
    // public String listaEventi() {
    //     return "index2";
    // }

}
