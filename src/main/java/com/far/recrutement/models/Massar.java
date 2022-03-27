package com.far.recrutement.models;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Massar implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3280546804756841420L;
    @Id
    @Column(length = 10)
    private String massarCode;
    @Column(length = 50)
    private String nom;
    @Column(length = 50)
    private String prenom;
    private float note;
    //private float note2;
    @Column(length = 10)
    private String cin;

    public Massar(String massarCode, String nom, String prenom, String cin, float note) {
        super();
        this.massarCode = massarCode;
        this.nom = nom;
        this.prenom = prenom;
        this.note = note;

        this.cin = cin;
    }

    public Massar() {
        super();
    }

    public String getMassarCode() {
        return massarCode;
    }

    public void setMassarCode(String massarCode) {
        this.massarCode = massarCode;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

}
