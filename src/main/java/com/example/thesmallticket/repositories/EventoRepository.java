package com.example.thesmallticket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.thesmallticket.model.Evento;

public interface EventoRepository extends JpaRepository<Evento,Long> {
    
}
