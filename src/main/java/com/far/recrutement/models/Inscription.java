package com.far.recrutement.models;


import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
public class Inscription implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 659965303574768245L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inscriptionID;
    @ManyToOne(fetch = FetchType.LAZY)
    private Candidat candidat;
    @ManyToOne
    private Concours concours;
    @ManyToOne
    private Statut statut;
    private String motif;
    private Date dateInscription;
    private Date dateConcours;
    private Long numeroConvocation;
    @Column(length = 45)
    private String convocation;
    @ManyToOne
    private Filiere filiere;
    private Boolean downloaded;
    private String heure;
    private Boolean nonrecu;

    public Inscription() {
        super();
        this.dateInscription = new Date();
        this.convocation = null;
    }

    public Inscription(Candidat candidat, Concours concours, Statut statut) {
        super();
        this.candidat = candidat;
        this.concours = concours;
        this.statut = statut;
        this.motif = "";
        this.dateInscription = new Date();
    }

    public Long getInscriptionID() {
        return inscriptionID;
    }

    public void setInscriptionID(Long inscriptionID) {
        this.inscriptionID = inscriptionID;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Concours getConcours() {
        return concours;
    }

    public void setConcours(Concours concours) {
        this.concours = concours;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Date getDateConcours() {
        if (dateConcours == null) {
            return new Date();
        }
        return dateConcours;
    }

    public void setDateConcours(Date dateConcours) {
        this.dateConcours = dateConcours;
    }

    public Long getNumeroConvocation() {
        if (numeroConvocation == null)
            return 0L;
        return numeroConvocation;
    }

    public void setNumeroConvocation(Long numeroConvocation) {
        this.numeroConvocation = numeroConvocation;
    }

    public String getConvocation() {
        return convocation;
    }

    public void setConvocation(String convocation) {
        this.convocation = convocation;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Boolean getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(Boolean downloaded) {
        this.downloaded = downloaded;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Boolean getNonrecu() {
        return nonrecu;
    }

    public void setNonrecu(Boolean nonrecu) {
        this.nonrecu = nonrecu;
    }
}