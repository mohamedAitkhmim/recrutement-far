package com.far.recrutement.models;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Concours implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1741399118614480804L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concoursID;
    private String titre;
    private String titreAr;
    @JsonIgnore
    @ManyToOne
    private Ecole ecoleFormation;
    @JsonIgnore
    @ManyToOne
    private Ecole ecoleTest;
    @ManyToOne
    private Armee armee;
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "concours")
    private Collection<Inscription> inscriptions;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date debut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fin;
    private boolean disponible;
    @Column(length = 1200)
    private String descriptionConcours;
    @Column(length = 1200)
    private String descriptionConcoursAr;
    @JsonIgnore
    @ManyToOne
    private Categorie categorie;
    private int nombrepost;
    private boolean resultat;
    private int agemax;
    private int agemin;
    private int bacancien;
    @Column(length = 12000)
    private String conditions;
    @Column(length = 12000)
    private String conditionsAr;
    @Column(length = 100)
    private String image;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateConcours;
    @JsonIgnore
    @ManyToMany
    private Collection<Niveau> niveaux;
    @JsonIgnore
    @ManyToMany
    private Collection<Sexe> sexe;
    @JsonIgnore
    @ManyToMany
    private Collection<Option> options;
    @JsonIgnore
    @OneToMany(mappedBy = "concours")
    private List<Filiere> filieres;
    private String avis;
    private String ens;

    public Concours() {
        super();
    }

    public Concours(String titre, String descriptionConcours, int nombrepost, String conditions, String image) {
        super();
        this.titre = titre;
        this.ecoleFormation = null;
        this.ecoleTest = null;
        this.debut = new Date();
        this.fin = new Date();
        this.disponible = true;
        this.descriptionConcours = descriptionConcours;
        this.categorie = null;
        this.nombrepost = nombrepost;
        this.resultat = false;
        this.agemax = 0;
        this.agemin = 0;
        this.bacancien = 0;
        this.conditions = conditions;
        this.image = image;
    }

    public String getTitreAr() {
        return titreAr;
    }

    public void setTitreAr(String titreAr) {
        this.titreAr = titreAr;
    }

    public String getDescriptionConcoursAr() {
        return descriptionConcoursAr;
    }

    public void setDescriptionConcoursAr(String descriptionConcoursAr) {
        this.descriptionConcoursAr = descriptionConcoursAr;
    }

    public String getConditionsAr() {
        return conditionsAr;
    }

    public void setConditionsAr(String conditionsAr) {
        this.conditionsAr = conditionsAr;
    }

    public Long getConcoursID() {
        return concoursID;
    }

    public void setConcoursID(Long concoursID) {
        this.concoursID = concoursID;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Ecole getEcoleFormation() {
        return ecoleFormation;
    }

    public void setEcoleFormation(Ecole ecoleFormation) {
        this.ecoleFormation = ecoleFormation;
    }

    public Ecole getEcoleTest() {
        return ecoleTest;
    }

    public void setEcoleTest(Ecole ecoleTest) {
        this.ecoleTest = ecoleTest;
    }

    public Collection<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(Collection<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getDescriptionConcours() {
        return descriptionConcours;
    }

    public void setDescriptionConcours(String descriptionConcours) {
        this.descriptionConcours = descriptionConcours;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public int getNombrepost() {
        return nombrepost;
    }

    public void setNombrepost(int nombrepost) {
        this.nombrepost = nombrepost;
    }

    public boolean isResultat() {
        return resultat;
    }

    public void setResultat(boolean resultat) {
        this.resultat = resultat;
    }

    public int getAgemax() {
        return agemax;
    }

    public void setAgemax(int agemax) {
        this.agemax = agemax;
    }

    public int getAgemin() {
        return agemin;
    }

    public void setAgemin(int agemin) {
        this.agemin = agemin;
    }

    public int getBacancien() {
        return bacancien;
    }

    public void setBacancien(int bacancien) {
        this.bacancien = bacancien;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getImage() {
        return "/images/concours/" + image;
    }

    @JsonIgnore
    public String getImageCode() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateConcours() {
        return dateConcours;
    }

    public void setDateConcours(Date dateConcours) {
        this.dateConcours = dateConcours;
    }

    public Collection<Niveau> getNiveaux() {
        return niveaux;
    }

    public void setNiveaux(Collection<Niveau> niveaux) {
        this.niveaux = niveaux;
    }

    public Collection<Sexe> getSexe() {
        return sexe;
    }

    public void setSexe(Collection<Sexe> sexe) {
        this.sexe = sexe;
    }

    public Collection<Option> getOptions() {
        return options;
    }

    public void setOptions(Collection<Option> options) {
        this.options = options;
    }

    @JsonIgnore
    public String getFins() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(fin);
    }

    public Armee getArmee() {
        return armee;
    }

    public void setArmee(Armee armee) {
        this.armee = armee;
    }

    public List<Filiere> getFilieres() {
        return filieres;
    }

    public void setFilieres(List<Filiere> filieres) {
        this.filieres = filieres;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public Boolean getVisible() {
        Calendar c = Calendar.getInstance();
        //c.add(Calendar.DAY_OF_MONTH, -1);
        Date now = c.getTime();
        return this.disponible && now.before(this.fin) && now.after(this.debut);
    }

    public String getEns() {
        return ens;
    }

    public void setEns(String ens) {
        this.ens = ens;
    }
}
