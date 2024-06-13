package com.example.thesmallticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtente;
    private String username;
    private String password;
    // private String nome;
    // private String cognome;
    private String email;

    public Long getIdUtente() {
        return idUtente;
    }
    public void setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
    }
    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    // public String getNome() {
    //     return nome;
    // }
    // public void setNome(String nome) {
    //     this.nome = nome;
    // }
    // public String getCognome() {
    //     return cognome;
    // }
    // public void setCognome(String cognome) {
    //     this.cognome = cognome;
    // }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
