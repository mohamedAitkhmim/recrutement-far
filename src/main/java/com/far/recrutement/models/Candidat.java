package com.far.recrutement.models;


import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Candidat extends User {
    /**
     *
     */
    private static final long serialVersionUID = 621861050303917606L;
    private Date dateInscription;
    @Column(length = 10,unique=true)
    private String cin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "date de naissance est requise")
    private Date naissance;
    private int anneebac;
    @ManyToOne
    private Option option;
    @Column(length = 150)
    private String massar;

    @ManyToOne
    private Langue langue;
    @ManyToOne
    @NotNull(message = "la ville est requise")
    private Ville ville;
    @ManyToOne
    @NotNull(message = "la région est requise")
    private Region region;
    @Column(length = 150)
    @NotNull(message = "l'adresse est requise")
    @NotEmpty(message = "l'adresse est requise")
    private String adresse;
    @OneToMany(mappedBy = "candidat")
    private Collection<Inscription> inscriptions;
    @Column(length = 50)
    @NotNull
    @NotEmpty(message = "le numéro de téléphone est requis")
    private String telephone;
    @Column(length = 25)
    private String nationnalite;
    @Column(length = 50)
    @NotNull
    @NotEmpty
    @NotNull(message = "le nom est requis")
    private String nom;
    @Column(length = 50)
    @NotNull
    @NotEmpty
    @NotNull(message = "le prénom est requis")
    private String prenom;
    @NotNull
    @NotEmpty
    @NotNull(message = "le prénom est requis")
    private String prenomAr;
    @NotNull
    @NotEmpty
    @NotNull(message = "le nom est requis")
    private String nomAr;
    @NotNull
    @NotEmpty
    @NotNull(message = "le nom du père est requis")
    private String pere;
    @NotNull
    @NotEmpty
    @NotNull(message = "le nom de la mère est requis")
    private String mere;
    @NotNull
    @NotEmpty
    @NotNull(message = "le nom du père est requis")
    private String pereAr;
    @NotNull
    @NotEmpty
    @NotNull(message = "le nom de la mère est requis")
    private String mereAr;
    @ManyToOne
    @NotNull(message = "le sexe est requis")
    private Sexe sexe;
    //private Boolean dsport1;
   // private Boolean dsport2;
    @ManyToOne
    @NotNull(message = "la situation familliale est requise")
    private Situation situation;
    @ManyToOne
    @NotNull(message = "le niveau est requis")
    private Niveau niveau;
    private float note1;
    //private float note2;
    //private float note3;
    //private float note4;
    @Column(length = 38)
    String codeActivation;
    String codeRecovery;
    private String mention;
    //private String ens;
    private String optionMassar;

    //private String lieuIsta;

    private String etablissement; //etranger ou national
    private String etablissementNom; // null en cas d'etablissement national
    @ManyToOne
    private EtablissementCategory etablissementCategory;


    public Candidat() {
        super();
        dateInscription = new Date();
        setRole("ROLE_CANDIDAT");
        this.setEnabled(false);
        codeActivation = UUID.randomUUID().toString();
    }

    public Candidat(String email, String cin, String adresse, String telephone, String nom, String prenom) {
        super();
        setEmail(email);
        this.cin = cin;
        this.naissance = new Date();
        this.anneebac = 0;
        this.option = null;
        this.massar = "";
        this.langue = null;
        this.ville = null;
        this.adresse = adresse;
        this.telephone = telephone;
        this.nationnalite = "";
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = null;
        this.situation = null;
        this.niveau = null;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getEtablissementNom() {
        return etablissementNom;
    }

    public void setEtablissementNom(String etablissementNom) {
        this.etablissementNom = etablissementNom;
    }

    public EtablissementCategory getEtablissementCategory() {
        return etablissementCategory;
    }

    public void setEtablissementCategory(EtablissementCategory etablissementCategory) {
        this.etablissementCategory = etablissementCategory;
    }

    public String getCodeRecovery() {
        return codeRecovery;
    }

    public void setCodeRecovery(String codeRecovery) {
        this.codeRecovery = codeRecovery;
    }

    public String getPrenomAr() {
        return prenomAr;
    }

    public void setPrenomAr(String prenomAr) {
        this.prenomAr = prenomAr;
    }

    public String getNomAr() {
        return nomAr;
    }

    public void setNomAr(String nomAr) {
        this.nomAr = nomAr;
    }

    public String getPere() {
        return pere;
    }

    public void setPere(String pere) {
        this.pere = pere;
    }

    public String getMere() {
        return mere;
    }

    public void setMere(String mere) {
        this.mere = mere;
    }

    public String getPereAr() {
        return pereAr;
    }

    public void setPereAr(String pereAr) {
        this.pereAr = pereAr;
    }

    public String getMereAr() {
        return mereAr;
    }

    public void setMereAr(String mereAr) {
        this.mereAr = mereAr;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public int getAnneebac() {
        return anneebac;
    }

    public void setAnneebac(int anneebac) {
        this.anneebac = anneebac;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public String getMassar() {
        return massar;
    }

    public void setMassar(String massar) {
        this.massar = massar;
    }

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Collection<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(Collection<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNationnalite() {
        return nationnalite;
    }

    public void setNationnalite(String nationnalite) {
        this.nationnalite = nationnalite;
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

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }


    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public float getNote1() {
        return note1;
    }

    public void setNote1(float note1) {
        this.note1 = note1;
    }


    public int getAge() {
        if (naissance != null) {
            long ageInMillis = new Date().getTime() - naissance.getTime();
            return (int) (ageInMillis / 1000f / 3600f / 24f / 365.25f);
        } else {
            return 0;
        }
    }



    public String getCodeActivation() {
        return codeActivation;
    }

    public void setCodeActivation(String codeActivation) {
        this.codeActivation = codeActivation;
    }



    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }



    public String getOptionMassar() {
        return optionMassar;
    }

    public void setOptionMassar(String optionMassar) {
        this.optionMassar = optionMassar;
    }

}
