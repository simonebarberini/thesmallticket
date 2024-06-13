package com.example.thesmallticket.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.thesmallticket.model.Utente;
@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

}