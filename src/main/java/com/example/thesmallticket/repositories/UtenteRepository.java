package com.example.thesmallticket.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.thesmallticket.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

}