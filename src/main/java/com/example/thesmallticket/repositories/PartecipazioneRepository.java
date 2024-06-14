package com.example.thesmallticket.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.thesmallticket.model.Partecipazione;

public interface PartecipazioneRepository extends JpaRepository<Partecipazione, Long> {
    List<Partecipazione> findByEventoId(Long eventoId);
}
