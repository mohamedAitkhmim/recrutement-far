package com.far.recrutement.models;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Filiere implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filiereID;
    private String nom;
    private String nomAr;

    @ManyToOne
    private Concours concours;

    public Filiere(String nom, String nomAr, Concours concours) {
        this.nom = nom;
        this.nomAr = nomAr;
        this.concours = concours;
    }

    public Long getFiliereID() {
        return filiereID;
    }

    public void setFiliereID(Long filiereID) {
        this.filiereID = filiereID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomAr() {
        return nomAr;
    }

    public void setNomAr(String nomAr) {
        this.nomAr = nomAr;
    }

    public Concours getConcours() {
        return concours;
    }

    public void setConcours(Concours concours) {
        this.concours = concours;
    }

    public Filiere() {
    }

    public String toString() {
        return nom;
    }
}