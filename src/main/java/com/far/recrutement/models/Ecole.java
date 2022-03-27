package com.far.recrutement.models;


import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Ecole implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7770755428578396771L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ecoleID;
    private String nomEcole;
    private String nomEcoleAr;
    @Column(length = 12000)
    private String descriptionEcole;
    @Column(length = 12000)
    private String descriptionEcoleAr;
    @ManyToMany
    private Collection<Categorie> categories;

    @OneToMany(mappedBy = "ecoleTest")
    private Collection<Concours> concours;
    private String insigne;

    @Column(length = 1000)
    private String directeur;
    @Column(length = 1000)
    private String addresse;
    private String ville;
    private String cachet;
    private String telephone;

    public Ecole() {
        super();
    }

    public Ecole(String nomEcole, String descriptionEcole, String nomEcoleAr, String descriptionEcoleAr, Collection<Categorie> categories, String insigne, String addresse, String directeur, String ville) {
        super();
        this.nomEcole = nomEcole;
        this.descriptionEcole = descriptionEcole;
        this.categories = categories;
        this.nomEcoleAr = nomEcoleAr;
        this.descriptionEcoleAr = descriptionEcoleAr;
        this.insigne = insigne;
        this.addresse = addresse;
        this.directeur = directeur;
        this.ville = ville;
    }

    public String getNomEcoleAr() {
        return nomEcoleAr;
    }

    public void setNomEcoleAr(String nomEcoleAr) {
        this.nomEcoleAr = nomEcoleAr;
    }

    public String getDescriptionEcoleAr() {
        return descriptionEcoleAr;
    }

    public void setDescriptionEcoleAr(String descriptionEcoleAr) {
        this.descriptionEcoleAr = descriptionEcoleAr;
    }

    public void setCategories(Collection<Categorie> categories) {
        this.categories = categories;
    }

    public Long getEcoleID() {
        return ecoleID;
    }


    public void setEcoleID(Long ecoleID) {
        this.ecoleID = ecoleID;
    }

    public String getNomEcole() {
        return nomEcole;
    }

    public void setNomEcole(String nomEcole) {
        this.nomEcole = nomEcole;
    }

    public String getDescriptionEcole() {
        return descriptionEcole;
    }

    public void setDescriptionEcole(String descriptionEcole) {
        this.descriptionEcole = descriptionEcole;
    }

    public Collection<Categorie> getCategories() {
        return categories;
    }

    public void setCategorie(Collection<Categorie> categories) {
        this.categories = categories;
    }

    public Collection<Concours> getConcours() {
        return concours;
    }

    public void setConcours(Collection<Concours> concours) {
        this.concours = concours;
    }

    @Override
    public String toString() {
        return nomEcole;
    }

    public String getInsigne() {
        return insigne;
    }

    public void setInsigne(String insigne) {
        this.insigne = insigne;
    }

    public String getDirecteur() {
        return directeur;
    }

    public void setDirecteur(String directeur) {
        this.directeur = directeur;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCachet() {
        return cachet;
    }

    public void setCachet(String cachet) {
        this.cachet = cachet;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}